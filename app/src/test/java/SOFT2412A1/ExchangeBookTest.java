package SOFT2412A1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeBookTest {
    private static ExchangeBook simpleBook;

    @BeforeAll
    static void setup() {
        FileHandler fileHandler = new FileHandler("src/test/resources/test1.json");
        try {
            simpleBook = fileHandler.read();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Test
    void getDailyRatesSimple() {
        List<Currency> expected = Arrays.asList(
                new Currency("AUD", 0.673),
                new Currency("USD", 1));

        assertEquals(simpleBook.getDailyRates("2022-09-08").getData(), expected);
    }

    @Test
    void getCurrencySimple() {
        Currency expected = new Currency("AUD", 0.673);
        assertEquals(simpleBook.getDailyRates("2022-09-08").getCurrency("AUD"), expected);
    }

    @Test
    void getCurrencyNull() {
        assertNull(simpleBook.getCurrency("2000-11-11", "USD"));
        assertNull(simpleBook.getCurrency("2022-09-08", "Random"));
    }

	@Test
	void getDailyRates() {
        assertNotNull(simpleBook.getDailyRates("2022-09-08"));
        assertNotNull(simpleBook.getDailyRates("2022-09-09"));
	}

    @Test
    void convertCurrencySimple() {
        Currency oldCurrency = new Currency("SDP", 1.9);
        Currency newCurrency = new Currency("PSD", 3.8);
        assertEquals(simpleBook.convertCurrency(oldCurrency, newCurrency, 10.5),
                "10.50 in SDP converts to 21.00 in PSD.");
    }


//    @Test
//    void addDailyRatesSimple() {
//        List<Currency> currencyList = Arrays.asList(
//                new Currency("AUD", 0.999),
//                new Currency("USD", 1.01));
//
//        DailyRates newDailyRates = new DailyRates("2022-09-09", currencyList);
//        simpleBook.addDailyRates(newDailyRates);
//
//        FileHandler newFileHandler = new FileHandler("src/test/resources/test3.json");
//
//        simpleBook.addDailyRates(newDailyRates);
//        try {
//            ExchangeBook expected = newFileHandler.read();
//            assertEquals(simpleBook.getExchangeRates().get(0), expected.getExchangeRates().get(0));
//            assertEquals(simpleBook.getExchangeRates().get(1), expected.getExchangeRates().get(1));
//        } catch (Exception e) {
//            throw new AssertionError(e);
//        }
//    }


    @Test
    void getIndex() {
        DailyRates expected = simpleBook.getExchangeRates().get(0);
        assertEquals(simpleBook.getIndex(0), expected);
        assertNull(simpleBook.getIndex(99));
    }
    @Test
    void equalSimple() {
        List<DailyRates> expected = simpleBook.getExchangeRates();
        assertEquals(simpleBook, expected);
    }

    @Test
    void toStringSimple() {
        String expected = "2022-09-08\n\tAUD: 0.673\n\tUSD: 1.0\n\nAUD\nSGD\nUSD\nEU\n";
        assertEquals(simpleBook.toString(), expected);
    }
}