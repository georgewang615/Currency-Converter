package SOFT2412A1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
	private static final long DAY_LENGTH_MS = 1000 * 60 * 60 * 24;
	public static String toString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static Date toDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getNextDate(Date date) {
		return new Date(date.getTime() + DAY_LENGTH_MS);
	}

	public static Date getCurrentDate() {
		return new Date();
	}
}
