package elements;

import models.PopulationCounter;

public class Tram {

    private int number;

    private Position currentPosition;

    private Route route;

    private PopulationCounter populationCounter;

    public Tram(Position currentPosition, Route route, PopulationCounter populationCounter) {
        this.currentPosition = currentPosition;
        this.route = route;
        this.populationCounter = populationCounter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
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
