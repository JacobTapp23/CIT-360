package edu.byui.cit360.calculators;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.byui.cit360.units.Quantity;
import edu.byui.cit360.units.World;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() throws Exception {
		// Context of the app under test.
		Context appCtx = InstrumentationRegistry.getTargetContext();

		assertEquals("edu.byui.cit360.calculators", appCtx.getPackageName());
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
}
