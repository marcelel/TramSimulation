package core;

import java.math.BigDecimal;

    protected final BigDecimal initialValue;
    protected BigDecimal scalar;
    protected BigDecimal weight;
    protected BigDecimal value;

    public Factor(BigDecimal initialValue) {
        this.initialValue = initialValue;
        scalar = initialValue;
    }

    public BigDecimal getInitialValue() {
        return initialValue;
    }

    public BigDecimal getScalar() {
        return scalar;
    }

    public void setScalar(BigDecimal scalar) {
        this.scalar = scalar;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void updateWeight(BigDecimal scalar) {
        setScalar(scalar);
        weight = weight.multiply(scalar).divide(initialValue);
    }

    public BigDecimal calculatePopulation() {
        return value.multiply(weight);
    }

    public abstract BigDecimal calculateValue();

}
