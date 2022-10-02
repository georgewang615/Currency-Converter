package SOFT2412A1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DailyRatesTest {
	private static DailyRates simpleRates;
	@BeforeAll
	static void setup() {
		FileHandler fileHandler = new FileHandler("src/test/resources/test1.json");
		try {
			simpleRates = fileHandler.read().getExchangeRates().get(0);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	@Test
	void getCurrencySimple() {
		Currency expected = new Currency("USD", 1);
		assertEquals(simpleRates.getCurrency("USD"), expected);
		assertNull(simpleRates.getCurrency("INVALID"));
	}
	@Test
	void getDataSimple() {
		List<Currency> currencyList = Arrays.asList(
			new Currency("AUD", 0.673),
			new Currency("USD", 1));

		DailyRates expected = new DailyRates("2022-09-08", currencyList);
		assertEquals(simpleRates.getData(), expected.getData());
	}

	@Test
	void getRatioGridSimple() {
		List<List<Double>> expected = new ArrayList<>();
		List<Double> n1 = Arrays.asList(0.0, 0.673);
		List<Double> n2 = Arrays.asList(1.4858841010401187, 0.0);
		expected.add(n1);
		expected.add(n2);
		assertEquals(simpleRates.getRatioGrid(Arrays.asList("AUD", "USD")), expected);
	}

	@Test
	void toStringSimple() {
		String expected = "2022-09-08\n\tAUD: 0.673\n\tUSD: 1.0\n";
		assertEquals(simpleRates.toString(), expected);
	}
}