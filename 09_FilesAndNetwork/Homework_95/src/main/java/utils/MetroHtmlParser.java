package utils;

import core.Line;
import core.Metro;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetroHtmlParser {

    private HashMap<String, Line> lineMatches = new HashMap<>();
    private HashMap<String, Station> stationMatches = new HashMap<>();

    public Metro parse(String url) {

        Metro metro = new Metro();
        try {
            Document document = Jsoup.connect(url).maxBodySize(0).get();
            Element metrodata = document.getElementById("metrodata");
            parseLines(metro, metrodata);
            parseStations(metro, metrodata);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return metro;
    }

    private void parseLines(Metro metro, Element metrodata) {
        Elements linesElements = metrodata.select("span[data-line]");
        for (int index = 0; index < linesElements.size(); index++) {
            Element elementLine = linesElements.get(index);
            Line line = new Line(elementLine.text(), elementLine.attr("data-line"), index);
            lineMatches.put(line.getNumber(), line);
            metro.addLine(line);
        }
    }

    private void parseStations(Metro metro, Element metrodata) {
        metro.getLines().forEach(line -> {
            Elements stationElements = metrodata.select(String.format("div[data-line=%s]",
                    line.getNumber())).select("a[data-metrost]");
            for (Element elementStation : stationElements
            ) {
                Station station = new Station(elementStation.select("span.name").text(), line);
                line.addStation(new Station(elementStation.select("span.name").text(), line));
                stationMatches.put(station.getName(), station);
                parseConnections(elementStation, station, metro);
            }
        });
    }

    private void parseConnections(Element elementStation, Station station, Metro metro) {

        List<Station> connections = new ArrayList<>();

        Pattern pattern = Pattern.compile("[\\S\\s]+«(?<stationname>[\\S\\s]+)»[\\S\\s]+");
        Elements connectionElements = elementStation.select("span[class~=t-icon-metroln]");
        connectionElements.forEach(connectionElement -> {
            Matcher matcher = pattern.matcher(connectionElement.attr("title"));
            if (matcher.matches()) {
                Station connectedStation = parseStation(matcher.group("stationname"),
                        connectionElement
                                .attr("class")
                                .replaceAll("t-icon-metroln ln-", ""));
                if (station != null) {
                    connections.add(connectedStation);
                    stationMatches.put(connectedStation.getName(), connectedStation);
                }
            }
        });
        if (connections.size() > 0) {
            connections.add(station);
            metro.addConnection(connections);
        }
    }

    private Station parseStation(String stationName, String lineNumber) {
        Line line = lineMatches.get(lineNumber);
        if (line == null) return null;
        Station station = null;
        if (stationMatches.containsKey(stationName)) {
            station = stationMatches.get(stationName);
            if (!station.getLine().getNumber().equals(lineNumber)) {
                return station = new Station(stationName, line);
            }
        } else {
            station = new Station(stationName, line);
        }
        return station;
    }


}
