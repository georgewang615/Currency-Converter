package SOFT2412A1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCurrencyTest {
	private ExchangeBook eb;

	@BeforeEach
	void setup() throws Exception {
		FileHandler fh = new FileHandler("src/test/resources/test1.json");
		eb = fh.read();
	}

	@Test
	void testAddCurrencyNewDate() {
		Currency newCurrency = new Currency("NZD", 0.89);
		eb.addCurrency(newCurrency);
		String currentDate = DateHandler.toString(DateHandler.getCurrentDate());
		assertEquals(eb.getCurrency(currentDate, "NZD"), newCurrency);
		assertEquals(eb.getExchangeRates().size(), 2);
	}

	@Test
	void testAddCurrencyExistingDate() {
		Currency newCurrency1 = new Currency("NZD", 0.89);
		Currency newCurrency2 = new Currency("INR", 0.18);
		eb.addCurrency(newCurrency1);
		eb.addCurrency(newCurrency2);
		String currentDate = DateHandler.toString(DateHandler.getCurrentDate());

		assertEquals(eb.getCurrency(currentDate, "NZD"), newCurrency1);
		assertEquals(eb.getCurrency(currentDate, "INR"), newCurrency2);
		assertEquals(eb.getExchangeRates().size(), 2);
	}
}
