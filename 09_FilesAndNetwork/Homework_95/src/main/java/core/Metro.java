package core;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

@ToString(includeFieldNames = false)
@Getter
public class Metro {

    private List<Line> lines;
    private List<List<Station>> connections;

    public Metro() {
        lines = new ArrayList<>();
        connections = new ArrayList<>();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void addLines(List<Line> lines) {
        lines.forEach(line -> addLine(line));
    }

    public void addConnection(List<Station> stations)
    {
        connections.add(stations);
    }

}
