package utils;

import core.Line;
import core.Metro;
import core.Station;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroJsonReader {

    private Map<String, Line> lineMatches = new HashMap<>();

    public Metro read(String filename) {
        Metro metro = new Metro();
        try {
            String source = Files.readString(Paths.get(filename));
            JSONParser metroJsonParser = new JSONParser();
            JSONObject rootObject = (JSONObject) metroJsonParser.parse(source);
            parseLines(rootObject, metro);
            parseStations(rootObject, metro);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return metro;
    }

    private void parseLines(JSONObject object, Metro metro) {
        List<Line> lines = new ArrayList<>();
        JSONArray linesObjects = (JSONArray) object.get("lines");
        for (int index = 0; index < linesObjects.size(); index++) {
            JSONObject lineObject = (JSONObject) linesObjects.get(index);

            Line line = new Line(lineObject.get("name").toString(),
                    lineObject.get("number").toString(),
                    Integer.parseInt(lineObject.get("id").toString()));
            lines.add(line);
            lineMatches.put(line.getNumber(), line);
        }
        metro.addLines(lines);
    }

    private void parseStations(JSONObject object, Metro metro) {
        JSONObject stationsJsonObject = (JSONObject) object.get("stations");
        stationsJsonObject.keySet().forEach(linenumber -> {
            if (lineMatches.containsKey(linenumber)) {
                Line line = lineMatches.get(linenumber);
                JSONArray lineStationsJsonObject = (JSONArray) stationsJsonObject.get(linenumber);
                lineStationsJsonObject.forEach(stationJsonItem -> {
                    line.addStation(new Station(stationJsonItem.toString(), line));
                });
            }
        });
    }

}
