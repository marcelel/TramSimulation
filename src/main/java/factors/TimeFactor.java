package factors;

import core.Factor;
import elements.Tram;

import java.math.BigDecimal;

public class TimeFactor extends Factor {

    public TimeFactor(BigDecimal initialValue) {
        super(initialValue);
    }

    @Override
    public BigDecimal calculateValue(Tram tram) {
        return null;
    }

}
