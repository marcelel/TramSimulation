package elements;

import models.PopulationCounter;

public class Tram {

    private CurrentPosition currentPosition;

    private Route route;

    private PopulationCounter populationCounter;

    public Tram(CurrentPosition currentPosition, Route route, PopulationCounter populationCounter) {
        this.currentPosition = currentPosition;
        this.route = route;
        this.populationCounter = populationCounter;
    }

    public CurrentPosition getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(CurrentPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public PopulationCounter getPopulationCounter() {
        return populationCounter;
    }

    public void setPopulationCounter(PopulationCounter populationCounter) {
        this.populationCounter = populationCounter;
    }
}
