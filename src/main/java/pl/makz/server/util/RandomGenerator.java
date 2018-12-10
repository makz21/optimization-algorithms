package pl.makz.server.util;

import java.util.Random;

public class RandomGenerator {

	private static final Random random = new Random();

	public static double randomDouble(double rangeMin, double rangeMax) {
		return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
	}
}
