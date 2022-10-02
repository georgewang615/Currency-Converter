package SOFT2412A1;
import java.text.*;
import java.util.*;


public class ExchangeBook {
    private List<DailyRates> exchangeRates;
    private List<String> popularCurrencies;

    public ExchangeBook(List<DailyRates> rates, List<String> popularCurrencies) {
        this.exchangeRates = rates;
        this.popularCurrencies = popularCurrencies;
    }

    public void addDailyRates(DailyRates dr) {

//        only have 1 entry per day
//        if a new entry is added on the same day, then remove the previous entry
        DailyRates ratesToRemove = null;
        for (DailyRates dailyRates : this.exchangeRates) {
            if (dailyRates.getDate().equals(dr.getDate())) {
                ratesToRemove = dailyRates;
            }
        }

        if (ratesToRemove != null) {
            this.exchangeRates.remove(ratesToRemove);
        }

        this.exchangeRates.add(dr);
    }

    public void addCurrency(Currency currency) {
        String currentDate = DateHandler.toString(DateHandler.getCurrentDate());
        List<Currency> currencyList = this.getDailyRates(currentDate).copyData();
        currencyList.add(currency);
        DailyRates newRates = new DailyRates(currentDate, currencyList);
        this.addDailyRates(newRates);
    }

    public List<DailyRates> getExchangeRates() {
        Collections.sort(this.exchangeRates, new Comparator<DailyRates>() {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            @Override
            public int compare(DailyRates dr1, DailyRates dr2) {
                try {
                    return df.parse(dr2.getDate()).compareTo(df.parse(dr1.getDate()));
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        return this.exchangeRates;
    }

    public List<String> getPopularCurrencies() {
        return this.popularCurrencies;
    }

    public void setPopularCurrencies(List<String> popularCurrencies) {
        this.popularCurrencies = popularCurrencies;
    }

    public DailyRates getIndex(int index) {
        if (index >= this.exchangeRates.size()) {
            return null;
        }

        return getExchangeRates().get(index);
    }


    // gets the list of currencies from the given date
//    if there are no entries from the given date, then it returns the most recent entry before that date.
//    if there are no entries at/before that date, then it returns null.
    public DailyRates getDailyRates(String date) {
        List<DailyRates> sortedRates = new ArrayList<DailyRates>(this.exchangeRates);
        sortedRates.sort(Comparator.comparing(DailyRates::getDate));

        for (int i = 0; i < sortedRates.size(); i++) {
            if (sortedRates.get(i).getDate().compareTo(date) == 0) {
                return sortedRates.get(i);

            } else if (sortedRates.get(i).getDate().compareTo(date) > 0) {
                if (i == 0) {
                    return null;
                } else {
                    return sortedRates.get(i-1);
                }
            }
        }

        if (sortedRates.get(sortedRates.size()-1).getDate().compareTo(date) < 0) {
            return sortedRates.get(sortedRates.size()-1);
        }

        return null;
    }

    // gets the currency object with the given name, from the given date
    public Currency getCurrency(String date, String name) {
        if (this.getDailyRates(date) == null) {
            return null;
        }

        for (Currency currency : this.getDailyRates(date).getData()) {
            if (currency.getName().equals(name)) {
                return currency;
            }
        }
        return null;
    }

    public String convertCurrency(Currency oldCurrency, Currency newCurrency, double amount) {

        double rate = newCurrency.getValue() / oldCurrency.getValue();
        double newAmount = amount * rate;
        return String.format("%.2f in %s converts to %.2f in %s.",
                amount, oldCurrency.getName(), newAmount, newCurrency.getName());
    }

    public String conversionRateSummary(Date startDate, Date endDate, Currency fromCurrency, Currency toCurrency) {
        String summary = "";
        summary += String.format("Comparing the exchange rates from %s to %s between %s and %s:\n\n",
            fromCurrency.getName(), toCurrency.getName(),
            DateHandler.toString(startDate), DateHandler.toString(endDate));

        Date movingDate = startDate;
        List<Double> exchangeRates = new ArrayList<>();

        while (movingDate.compareTo(endDate) != 1) {
            String currentDateString = DateHandler.toString(movingDate);

            Currency currentCurrency1 = this.getCurrency(currentDateString, fromCurrency.getName());
            Currency currentCurrency2 = this.getCurrency(currentDateString, toCurrency.getName());
            double currentExchangeRate = currentCurrency1.exchangeRate(currentCurrency2);

            summary += String.format("%s: %.2f\n", currentDateString, currentExchangeRate);
            exchangeRates.add(currentExchangeRate);

            movingDate = DateHandler.getNextDate(movingDate);
        }

        summary += "\n";
        summary += String.format("Min: %.2f\n", RateSummary.min(exchangeRates));
        summary += String.format("Max: %.2f\n", RateSummary.max(exchangeRates));
        summary += String.format("Mean: %.2f\n", RateSummary.mean(exchangeRates));
        summary += String.format("Standard deviation: %.2f\n", RateSummary.sd(exchangeRates));

        return summary;
    }

    @Override
    public boolean equals(Object other) {
        return this.getExchangeRates().equals(other);
    }

    @Override
    public String toString() {
        String result = "";
        for (DailyRates dailyRates : this.exchangeRates) {
            result = result.concat(String.format("%s\n", dailyRates.toString()));
        }
        for (String currency : this.popularCurrencies) {
            result = result.concat(String.format("%s\n", currency));
        }

        return result;
    }
}
