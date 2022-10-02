package SOFT2412A1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public ExchangeBook read() throws Exception {
        List<DailyRates> rates = new ArrayList<>();
        List<String> popularCurrencies = new ArrayList<>();
        JSONParser parser = new JSONParser();

        JSONObject data = (JSONObject) parser.parse(new FileReader(this.fileName));
        JSONArray days = (JSONArray) data.get("rates");
        JSONArray popular = (JSONArray) data.get("popular");
        for (Object day : days) {
            JSONObject dayJSON = (JSONObject) day;
            List<Currency> currencies = parseCurrencies(dayJSON);
            DailyRates dr = new DailyRates(dayJSON.get("date").toString(), currencies);
            rates.add(dr);
        }

        for (Object popularCurrency : popular) {
            popularCurrencies.add((String) popularCurrency);
        }

        return new ExchangeBook(rates, popularCurrencies);
    }

    public void write(ExchangeBook newBook) throws Exception {
        FileWriter writer = new FileWriter(this.fileName);
        writer.write(convertToJSON(newBook).toJSONString());
        writer.close();
    }

    private static List<Currency> parseCurrencies(JSONObject day) {
        List<Currency> currencies = new ArrayList<Currency>();
        JSONArray currencyListJSON = (JSONArray) day.get("currencies");
        for (Object currency : currencyListJSON) {
            JSONObject currencyJSON = (JSONObject) currency;
            currencies.add(new Currency(
                currencyJSON.get("name").toString(),
                Double.parseDouble(currencyJSON.get("value").toString())));
        }

        return currencies;
    }

    private static JSONObject convertToJSON(ExchangeBook eb) {
        JSONObject dataJSON = new JSONObject();
        JSONArray ratesJSON = new JSONArray();
        JSONArray popularJSON = new JSONArray();
        for (String name : eb.getPopularCurrencies()) {
            popularJSON.add(name);
        }

        for (DailyRates dr : eb.getExchangeRates()) {
            JSONObject day = new JSONObject();
            JSONArray currenciesJSON = new JSONArray();
            for (Currency c : dr.getData()) {
                JSONObject currencyJSON = new JSONObject();
                currencyJSON.put("name", c.getName());
                currencyJSON.put("value", c.getValue());
                currenciesJSON.add(currencyJSON);
            }

            day.put("date", dr.getDate());
            day.put("currencies", currenciesJSON);
            ratesJSON.add(day);
        }

        dataJSON.put("rates", ratesJSON);
        dataJSON.put("popular", popularJSON);
        return dataJSON;
    }
}
