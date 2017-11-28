package models;

import core.Factor;
import factors.LineFactor;
import factors.StopFactor;
import factors.TimeFactor;
import factors.WeatherFactor;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PopulationCounter {

    private BigDecimal population;

    Factor[] factors;

    public PopulationCounter(BigDecimal population) {
        this.population = population;
        factors = new Factor[4];
        factors[0] = new LineFactor(population);
        factors[1] = new StopFactor(population);
        factors[2] = new TimeFactor(population);
        factors[3] = new WeatherFactor(population);

    }

    public void updatePopulation() {
        //to optimize
        BigDecimal accumulator = BigDecimal.ZERO;
        accumulator.add(population);
        for (int i = 0; i < factors.length; i++) {
            accumulator.add(factors[i].calculatePopulation());
        }
        population = accumulator;
        updateAllFactorWeights();
    }

    private void updateAllFactorWeights() {
        for (int i = 0; i < factors.length; i++) {
            factors[i].updateWeight(population);
        }
    }

}
