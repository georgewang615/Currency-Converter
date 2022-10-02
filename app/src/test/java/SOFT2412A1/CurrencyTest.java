package SOFT2412A1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
	private static Currency simpleCurrency;

	@BeforeAll
	static void setup() {
		simpleCurrency = new Currency("AUD", 0.673);
	}

	@Test
	void getName() {
		assertEquals(simpleCurrency.getName(), "AUD");
	}

	@Test
	void getValue() {
		assertEquals(simpleCurrency.getValue(), 0.673);
	}

	@Test
	void testEquals() {
		Currency same = new Currency("AUD", 0.673);
		Currency diffName = new Currency("USD", 0.673);
		Currency diffValue = new Currency("AUD", 1);

		assertEquals(simpleCurrency, same);
		assertNotEquals(simpleCurrency, diffName);
		assertNotEquals(simpleCurrency, diffValue);
	}
}