package elements;

import elements.Stop;

import java.util.List;

public class Route {

    private List<Stop> stops;

    public Route(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
