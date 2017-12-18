package factors;

import core.Factor;
import elements.Tram;

import java.math.BigDecimal;

public class StopFactor extends Factor {

    public StopFactor(BigDecimal initialValue) {
        super(initialValue);
    }

    @Override
    public BigDecimal calculateValue(Tram tram) {
        return null;
    }

}
