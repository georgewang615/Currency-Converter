package SOFT2412A1;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileHandlerTest {
	private static ExchangeBook simpleExchangeBook;

	@BeforeAll
	static void setup() throws Exception {
		List<Currency> currencies = new ArrayList<>();
		currencies.add(new Currency("AUD", 0.673));
		currencies.add(new Currency("USD", 1));
		DailyRates simpleDailyRates = new DailyRates("2022-09-08", currencies);
		List<DailyRates> exchangeRates = new ArrayList<>();
		exchangeRates.add(simpleDailyRates);
		List<String> popularCurrencies = Arrays.asList(
			"AUD", "SGD", "USD", "EU"
		);

		simpleExchangeBook = new ExchangeBook(exchangeRates, popularCurrencies);

		// empty the file to write to
		new FileWriter("src/test/resources/test2.json", false).close();
	}

	@Test
	void readSimple() throws Exception {
		FileHandler fileHandler = new FileHandler("src/test/resources/test1.json");
		ExchangeBook output = fileHandler.read();

		List<Currency> actual = output.getDailyRates("2022-09-08").getData();
		List<Currency> expected = simpleExchangeBook.getDailyRates("2022-09-08").getData();
		assertEquals(actual, expected);
	}

	@Test
	void writeSimple() throws Exception {
		FileHandler fileHandler = new FileHandler("src/test/resources/test2.json");
		fileHandler.write(simpleExchangeBook);
		JSONParser parser = new JSONParser();
		JSONObject input = (JSONObject) parser.parse(new FileReader("src/test/resources/test1.json"));
		JSONObject output = (JSONObject) parser.parse(new FileReader("src/test/resources/test2.json"));
		assertEquals(input, output);
	}
}