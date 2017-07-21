package edu.byui.cit360.calculators;

import org.junit.Test;
import edu.byui.cit360.units.*;
import static org.junit.Assert.assertEquals;

public class Units {
	@Test
	public void testLength() {
		Quantity prop = World.getInstance().get("length");
		assertEquals(12, prop.convert("inch", 1, "foot"), 0.1);
		assertEquals(36, prop.convert("inch", 1, "yard"), 0.1);
		assertEquals(3, prop.convert("foot", 1, "yard"), 0.1);
		assertEquals(5280, prop.convert("foot", 1, "mile"), 0.1);
		assertEquals(320, prop.convert("rod", 1, "mile"), 0.1);

		assertEquals(2, prop.convert("foot", 24, "inch"), 0.1);
		assertEquals(2, prop.convert("yard", 72, "inch"), 0.1);
		assertEquals(3, prop.convert("yard", 9, "foot"), 0.1);
		assertEquals(1, prop.convert("mile", 5280, "foot"), 0.1);
		assertEquals(2, prop.convert("mile", 640, "rod"), 0.1);

		assertEquals(3.1, prop.convert("mile", 5, "kilometer"), 0.1);
		assertEquals(10, prop.convert("kilometer", 6.2, "mile"), 0.1);

		assertEquals(6000, prop.convert("millimeter", 6, "meter"), 0.1);
		assertEquals( 600, prop.convert("centimeter", 6, "meter"), 0.1);
		assertEquals(   6, prop.convert("meter", 6, "meter"), 0.1);
		assertEquals(6000, prop.convert("meter", 6, "kilometer"), 0.1);

		assertEquals(0.006, prop.convert("meter", 6, "millimeter"), 0.0001);
		assertEquals(0.06,  prop.convert("meter", 6, "centimeter"), 0.0001);
		assertEquals(0.006, prop.convert("kilometer", 6, "meter"), 0.0001);
	}

	@Test
	public void testMass() {
		Quantity prop = World.getInstance().get("mass");
		assertEquals(437.5, prop.convert("grain", 1, "ounce"), 0.01);
		assertEquals(32, prop.convert("ounce", 2, "pound"), 0.1);
		assertEquals(1, prop.convert("kilogram", 2.2, "pound"), 0.01);
		assertEquals(2000, prop.convert("gram", 2, "kilogram"), 0.1);
	}

	@Test
	public void testTime() {
		double epsilon = 0.000001;
		Quantity prop = World.getInstance().get("time");
		assertEquals(120, prop.convert("second", 2, "minute"), epsilon);
		assertEquals(3600, prop.convert("second", 1, "hour"), epsilon);
		assertEquals(24 * 60, prop.convert("minute", 1, "day"), epsilon);
		assertEquals(21, prop.convert("day", 3, "week"), epsilon);

		assertEquals(2, prop.convert("minute", 120, "second"), epsilon);
		assertEquals(1, prop.convert("hour", 3600, "second"), epsilon);
		assertEquals(2, prop.convert("day", 2 * 24 * 60, "minute"), epsilon);
		assertEquals(2, prop.convert("week", 14, "day"), epsilon);
	}

	/*
	public static void main(String[] args) {
		PrintStream out = java.lang.System.out;

		List<String> ugly = Arrays.asList("rod");
		for (Quantity prop : world.getAll()) {
			out.println(prop);
			for (edu.byui.cit360.units.System sys : prop.getAll()) {
				out.println("  " + sys);
				for (Unit unit : sys.getExcept(ugly)) {
					out.println("    " + unit);
				}
			}
		}

		{
			Quantity prop = world.get("length");
			out.println(prop.convert("foot", 24, "inch"));
			out.println(prop.convert("inch", 1.5, "foot"));
			out.println(prop.convert("yard", 36, "inch"));

			prop = world.get("mass");
			out.println(prop.convert("kilogram", 2.2, "pound"));
			out.println(prop.convert("pound", 1, "kilogram"));
			out.println(prop.convert("ounce", 1, "pound"));
			out.println(prop.convert("pound", 1, "metric ton"));
		}
	}
*/
}
