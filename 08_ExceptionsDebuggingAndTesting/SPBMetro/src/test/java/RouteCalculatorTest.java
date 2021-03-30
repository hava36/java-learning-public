import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    RouteCalculator routeCalculator;
    StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();

        Line redLine = new Line(1, "red");
        redLine.addStation(new Station("station 1 (red line)", redLine));
        redLine.addStation(new Station("station 2 (red line)", redLine));
        redLine.addStation(new Station("station 3 (red line)", redLine));
        redLine.addStation(new Station("station 4 (red line)", redLine));
        stationIndex.addLine(redLine);
        redLine.getStations().forEach(station -> stationIndex.addStation(station));

        Line greenLine = new Line(2, "green");
        greenLine.addStation(new Station("station 1 (green line)", greenLine));
        greenLine.addStation(new Station("station 2 (green line)", greenLine));
        greenLine.addStation(new Station("station 3 (green line)", greenLine));
        greenLine.addStation(new Station("station 4 (green line)", greenLine));
        stationIndex.addLine(greenLine);
        greenLine.getStations().forEach(station -> stationIndex.addStation(station));

        Line blueLine = new Line(3, "blue");
        blueLine.addStation(new Station("station 1 (blue line)", blueLine));
        blueLine.addStation(new Station("station 2 (blue line)", blueLine));
        blueLine.addStation(new Station("station 3 (blue line)", blueLine));
        blueLine.addStation(new Station("station 4 (blue line)", blueLine));
        stationIndex.addLine(blueLine);
        blueLine.getStations().forEach(station -> stationIndex.addStation(station));

        List<Station> connections1 = new ArrayList<>();
        connections1.add(blueLine.getStations().get(1));
        connections1.add(redLine.getStations().get(1));
        stationIndex.addConnection(connections1);

        List<Station> connections2 = new ArrayList<>();
        connections2.add(blueLine.getStations().get(2));
        connections2.add(greenLine.getStations().get(2));
        stationIndex.addConnection(connections2);

        routeCalculator = new RouteCalculator(stationIndex);

    }

    public void testCalculateDurationOnOneRoute() {
        double actual = RouteCalculator.calculateDuration(stationIndex.getLine(1).getStations());
        double expected = 7.5;
        assertEquals(expected, actual);
    }

    public void testCalculateDurationWithConnection() {

        List<Station> route = new ArrayList<>();
        route.addAll(stationIndex.getLine(1).getStations());
        route.addAll(stationIndex.getLine(2).getStations());

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 18.5;

        assertEquals(expected, actual);

    }

    public void testGetShortestRouteOnLine() {

        Station stationFrom = stationIndex.getLine(1).getStations().get(0);
        Station stationTo = stationIndex.getLine(1).getStations().get(3);

        List<Station> shortestRoute = routeCalculator.getShortestRoute(stationFrom, stationTo);
        assertEquals(4, shortestRoute.size());

        stationFrom = stationIndex.getLine(2).getStations().get(0);
        stationTo = stationIndex.getLine(2).getStations().get(3);

        shortestRoute = routeCalculator.getShortestRoute(stationFrom, stationTo);
        assertEquals(4, shortestRoute.size());

    }

    public void testGetShortestRouteWithOneConnection() {

        Station stationFrom = stationIndex.getLine(1).getStations().get(0);
        Station stationTo = stationIndex.getLine(3).getStations().get(3);

        List<Station> shortestRoute = routeCalculator.getShortestRoute(stationFrom, stationTo);
        assertEquals(5, shortestRoute.size());

    }

    public void testGetShortestRouteWithTwoConnections() {

        Station stationFrom = stationIndex.getLine(1).getStations().get(0);
        Station stationTo = stationIndex.getLine(2).getStations().get(3);

        List<Station> shortestRoute = routeCalculator.getShortestRoute(stationFrom, stationTo);
        assertEquals(6, shortestRoute.size());

    }

    public void testGetShortestRouteWithTwoConnectionsInreverseDirection() {

        Station stationTo = stationIndex.getLine(1).getStations().get(0);
        Station stationFrom = stationIndex.getLine(2).getStations().get(3);

        List<Station> shortestRoute = routeCalculator.getShortestRoute(stationFrom, stationTo);
        assertEquals(6, shortestRoute.size());

    }



    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
