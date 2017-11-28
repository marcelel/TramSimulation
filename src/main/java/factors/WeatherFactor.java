package factors;

import core.Factor;

import java.math.BigDecimal;

public class WeatherFactor extends Factor {

    public WeatherFactor(BigDecimal scalar) {
        super(scalar);
    }

    @Override
    public BigDecimal calculateValue() {
        return null;
    }

}
