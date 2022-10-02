package SOFT2412A1;

public class Currency {
    private String name;
    private double value;

    public Currency(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
    return this.name;
  }

    public double getValue() {
    return this.value;
  }

    public double exchangeRate(Currency other) {
        return this.getValue() / other.getValue();
    }
    public Currency copy() {
        return new Currency(this.name, this.value);
    }

    @Override
    public boolean equals(Object other) {
        Currency otherCurrency = (Currency) other;
        return (this.name.equals(otherCurrency.getName())
            && this.value == otherCurrency.getValue());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.name, this.value);
    }
}
