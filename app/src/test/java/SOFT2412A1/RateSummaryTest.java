package SOFT2412A1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RateSummaryTest {
	private static List<Double> list1;
	private static List<Double> list2;
	private static List<Double> list3;

	@BeforeAll
	static void setup() {
		list1 = new ArrayList<>();
		list2 = null;
		list3 = Arrays.asList(
			Double.valueOf(4),
			Double.valueOf(2),
			Double.valueOf(7),
			Double.valueOf(4),
			Double.valueOf(8),
			Double.valueOf(1),
			Double.valueOf(0.5),
			Double.valueOf(0.3),
			Double.valueOf(2.1),
			Double.valueOf(0.4)
		);
	}

	@Test
	void minTestNull() {
		assertEquals(RateSummary.min(list1), 0);
		assertEquals(RateSummary.min(list2), 0);
	}

	@Test
	void maxTestNull() {
		assertEquals(RateSummary.max(list1), 0);
		assertEquals(RateSummary.max(list2), 0);
	}

	@Test
	void meanTestNull() {
		assertEquals(RateSummary.mean(list1), 0);
		assertEquals(RateSummary.mean(list2), 0);
	}

	@Test
	void sdTestNull() {
		assertEquals(RateSummary.sd(list1), 0);
		assertEquals(RateSummary.sd(list2), 0);
	}

	@Test
	void min() {
		assertEquals(RateSummary.min(list3), 0.3);
	}

	@Test
	void max() {
		assertEquals(RateSummary.max(list3), 8);
	}

	@Test
	void mean() {
		String expected = "2.93";
		assertEquals(String.format("%.2f", RateSummary.mean(list3)), expected);
	}

	@Test
	void sd() {
		String expected = "2.63";
		assertEquals(String.format("%.2f", RateSummary.sd(list3)), expected);
	}
}