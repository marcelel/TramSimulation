package factors;

import core.Factor;
import elements.Tram;

import java.math.BigDecimal;

public class LineFactor extends Factor {

    public LineFactor(BigDecimal initialValue) {
        super(initialValue);
    }

    @Override
    public BigDecimal calculateValue(Tram tram) {
        return null;
    }

}
