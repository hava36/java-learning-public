package utils;

import core.Metro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class MetroJsonWriter {

    private Metro metro;

    public MetroJsonWriter(Metro metro) {
        this.metro = metro;
    }

    public void write(String filename) {
        JSONObject rootObject = new JSONObject();
        fillStations(rootObject);
        fillLines(rootObject);
        fillConnections(rootObject);
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(rootObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillStations(JSONObject rootObject) {
        JSONObject stations = new JSONObject();
        metro.getLines().forEach(line -> {
            JSONArray stationsList = new JSONArray();
            line.getStations().forEach(station -> {
                stationsList.add(station.getName());
            });
            stations.put(line.getNumber(), stationsList);
        });
        rootObject.put("stations", stations);
    }

    private void fillLines(JSONObject rootObject) {
        JSONArray linesList = new JSONArray();
        metro.getLines().forEach(line -> {
            JSONObject lineObject = new JSONObject();
            lineObject.put("number", line.getNumber());
            lineObject.put("name", line.getName());
            lineObject.put("id", line.getId());
            linesList.add(lineObject);
        });
        rootObject.put("lines", linesList);
    }

    private void fillConnections(JSONObject rootObject) {
        JSONArray connectionJsonList = new JSONArray();
        metro.getConnections().forEach(stations -> {
            JSONArray itemJsonArray = new JSONArray();
            stations.forEach(connectedStation -> {
                JSONObject stationJsonObject = new JSONObject();
                stationJsonObject.put("name", connectedStation.getName());
                stationJsonObject.put("line", connectedStation.getLine().getNumber());
                itemJsonArray.add(stationJsonObject);
            });
            connectionJsonList.add(itemJsonArray);
        });
        rootObject.put("connections", connectionJsonList);
    }
}
