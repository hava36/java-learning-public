package core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@NonNull
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Station implements Comparable<Station> {

    private String name;
    @ToString.Exclude private Line line;


    @Override
    public int compareTo(Station station)
    {
        int lineComparison = line.compareTo(station.getLine());
        if(lineComparison != 0) {
            return lineComparison;
        }
        return name.compareTo(station.getName());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Station) obj) == 0;
    }

}
