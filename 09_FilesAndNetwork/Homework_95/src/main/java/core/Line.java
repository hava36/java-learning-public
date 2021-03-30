package core;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@NonNull
@ToString(includeFieldNames = false)
public class Line implements Comparable<Line> {

    private String name;
    private String number;
    @ToString.Exclude private List<Station> stations;
    @ToString.Exclude private int id;

    public Line(String name, String number, int id) {
        this.name = name;
        this.number = number;
        this.stations = new ArrayList<>();
        this.id = id;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    @Override
    public int compareTo(Line line)
    {
        return Integer.compare(id, line.getId());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Line) obj) == 0;
    }

}
