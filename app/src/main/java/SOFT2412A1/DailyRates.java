package SOFT2412A1;

import java.util.ArrayList;
import java.util.List;

public class DailyRates {
    private String date;
    private List<Currency> data;

    public DailyRates(String date, List<Currency> data) {
        this.date = date; this.data = data;
    }

    public List<List<Double>> getRatioGrid(List<String> currencies) {
        List<List<Double>> grid = new ArrayList<>();

        for (String currency1 : currencies) {
            List<Double> row = new ArrayList<>();
            for (String currency2 : currencies) {
                if (currency1.equals(currency2)) {
                    row.add(0.0);
                    continue;
                }
                
                row.add(this.getCurrency(currency1).getValue() / this.getCurrency(currency2).getValue());
            }

            grid.add(row);
        }

        return grid;
    }
    public String getDate() { return this.date; }

    public List<Currency> getData() {
        return this.data;
    }

    public Currency getCurrency(String name) {
        for (Currency c : this.data) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public List<Currency> copyData() {
        List<Currency> newData = new ArrayList<Currency>();
        for (Currency currency : this.data) {
            newData.add(currency.copy());
        }
        return newData;
    }

    @Override
    public boolean equals(Object other) {
        DailyRates otherRates = (DailyRates) other;
        if (!this.getDate().equals(otherRates.getDate())) {
            return false;
        }

        return this.getData().equals(otherRates.getData());
    }

    @Override
    public String toString() {
        String result = String.format("%s\n", this.date);
        for (Currency currency : this.data) {
            result = result.concat(String.format("\t%s\n", currency.toString()));
        }
        return result;
    }
}
