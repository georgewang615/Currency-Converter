package SOFT2412A1;

import java.util.List;

public class RateSummary {

	public static double min(List<Double> rates) {
		if (rates == null || rates.size() == 0) {
			return 0;
		}
		double currentMin = rates.get(0);
		for (Double rate : rates) {
			if (rate < currentMin) {
				currentMin = rate;
			}
		}

		return currentMin;
	}

	public static double max(List<Double> rates) {
		if (rates == null || rates.size() == 0) {
			return 0;
		}
		double currentMax = rates.get(0);
		for (Double rate : rates) {
			if (rate > currentMax) {
				currentMax = rate;
			}
		}

		return currentMax;
	}

	public static double mean(List<Double> rates) {
		if (rates == null || rates.size() == 0) {
			return 0;
		}
		double sum = 0;
		for (Double rate : rates) {
			sum += rate;
		}

		return sum / rates.size();
	}

	public static double sd(List<Double> rates) {
		if (rates == null || rates.size() == 0) {
			return 0;
		}

		double mean = mean(rates);
		double sumSquares = 0;
		for (Double rate : rates) {
			sumSquares += Math.pow(rate - mean, 2);
		}

		return Math.sqrt(sumSquares / rates.size());
	}
}
