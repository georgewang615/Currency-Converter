package SOFT2412A1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateHandlerTest {
	private static DateFormat df;
	@BeforeAll
	static void setup() {
		df = new SimpleDateFormat("yyyy-MM-dd");
	}
	@Test
	void toStringTest() {
		Date currentDate = new Date();
		assertEquals(df.format(currentDate), DateHandler.toString(currentDate));
	}

	@Test
	void toDateTest() throws ParseException {
		String testDate = "2022-06-17";
		assertEquals(DateHandler.toDate(testDate), df.parse(testDate));
	}
}