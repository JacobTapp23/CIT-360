package edu.byui.cit.room.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static edu.byui.cit.room.model.Student.floatEquals;

public class StudentTest {
	@Test
	public void testFloatEquals() {
		assertEquals((int)Math.signum(-0F), 0);
		assertEquals((int)Math.signum(0F), 0);

		float t = 0.2513478F;
		float ulp = Math.ulp(t);
		float[][] negatives = {
				{1.1F, -1.1F},
				{0, Float.NaN},
				{Float.NaN, 0},
				{Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY},
				{Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY},
				{t, t - 3F * ulp},
				{t, t + 3F * ulp},
		};
		for (float[] values : negatives) {
			float e = values[0];
			float f = values[1];
			assertFalse(floatEquals(e, f, 2));
		}

		float[][] positives = {
				{Float.NaN, Float.NaN},
				{-0F, 0F},
				{Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY},
				{Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY},
				{1F/3F, 1F/3F},
				{t, t - ulp},
				{t, t + ulp},
		};
		for (float[] values : positives) {
			float e = values[0];
			float f = values[1];
			assertTrue(floatEquals(e, f, 2));
		}
	}
}