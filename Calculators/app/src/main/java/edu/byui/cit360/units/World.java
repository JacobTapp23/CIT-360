package edu.byui.cit360.units;

import android.content.res.Resources;
import android.util.Log;

import edu.byui.cit360.calculators.Calculators;
import edu.byui.cit360.calculators.R;

/** World is a singleton that contains units for all physical properties.
 * Several requirements contributed to the design of this units code:
 * 1. It only makes sense to convert among units that measure the same
 *    physical property.
 * 2. It must be easy to generate a list of units for a user to select from.
 * 3. Units must remain organized by physical property and unit system.
 * 4. Within a physical property, it must be possible to convert among
 *    units of the same system and different systems.
 */
public class World extends Container<Quantity> {
	private World() {
		super("all physical quantities");
	}

	private static World singleton;

	public static synchronized World getInstance() {
		if (singleton == null) {
			try {
				singleton = new World();
				final String
					cur = "current",
					temp = "temperature",
					lum = "luminous",

					freq = "frequency",
					ang = "angle",

					pres = "pressure",

					ener = "energy",
					pow = "power",
					volt = "voltage",
					resis = "resistance",

					fuel = "fuel usage",
					speed = "speed",
					accel = "acceleration",
					force = "force";

				final String
					customary = "customary",
					metric = "metric";

				final double mm = 1609344;
				Quantity quant = new Quantity("length");
				singleton.add(quant
					.add(new Unit(quant, "inch",       "in",  63360))
					.add(new Unit(quant, "foot",       "ft",  5280))
					.add(new Unit(quant, "yard",       "yd",  1760))
					.add(new Unit(quant, "rod",        "rod", 320))
					.add(new Unit(quant, "mile",       "mi",  1))
					.add(new Unit(quant, "millimeter", "mm",  mm))
					.add(new Unit(quant, "centimeter", "cm",  mm / 10))
					.add(new Unit(quant, "meter",      "m",   mm / 1000))
					.add(new Unit(quant, "kilometer",  "km",  mm / 1000000)));

				final double kg = 2240 / 2.20462262;
				quant = new Quantity("mass");
				singleton.add(quant
					.add(new Unit(quant, "grain",     "gr",    15680000))
					.add(new Unit(quant, "ounce",     "oz",    35840))
					.add(new Unit(quant, "pound",     "lb",    2240))
					.add(new Unit(quant, "stone",     "st",    160))
					.add(new Unit(quant, "shortTon",  "ton",   1.12))
					.add(new Unit(quant, "longTon",   "ton",   1))
					.add(new Unit(quant, "gram",      "g",     kg * 1000))
					.add(new Unit(quant, "kilogram",  "kg",    kg))
					.add(new Unit(quant, "metricTon", "tonne", kg / 1000)));

				quant = new Quantity("time");
				singleton.add(quant
					.add(new Unit(quant, "second", "s",   7 * 24 * 60 * 60))
					.add(new Unit(quant, "minute", "min", 7 * 24 * 60))
					.add(new Unit(quant, "hour",   "hr",  7 * 24))
					.add(new Unit(quant, "day",    "day", 7))
					.add(new Unit(quant, "week",   "wk",  1)));

				final double sqkm = 2.58999;
				quant = new Quantity("area");
				singleton.add(quant
					.add(new Unit(quant, "squareInch",       "sq in", 640.0 * 4840 * 3 * 3 * 12 * 12))
					.add(new Unit(quant, "squareFoot",       "sq ft", 640.0 * 4840 * 3 * 3))
					.add(new Unit(quant, "squareYard",       "sq yd", 640.0 * 4840))
					.add(new Unit(quant, "acre",             "acre",  640))
					.add(new Unit(quant, "squareMile",       "sq mi", 1))
					.add(new Unit(quant, "squareCentimeter", "sq cm", sqkm * 100 * 10000 * 10000))
					.add(new Unit(quant, "squareMeter",      "sq m",  sqkm * 100 * 10000))
					.add(new Unit(quant, "hectare",          "ha",    sqkm * 100))
					.add(new Unit(quant, "squareKilometer",  "sq km", sqkm)));

				final double cuft = 35.3147;
				final double gal = 7.48052;
				quant = new Quantity("volume");
				singleton.add(quant
					.add(new Unit(quant, "teaspoon",   "tsp",   cuft * gal * 4 * 2 * 2 * 8 * 2 * 3))
					.add(new Unit(quant, "tablespoon", "tbsp",  cuft * gal * 4 * 2 * 2 * 8 * 2))
					.add(new Unit(quant, "fluidOunce", "fl oz", cuft * gal * 4 * 2 * 2 * 8))
					.add(new Unit(quant, "cup",        "cup",   cuft * gal * 4 * 2 * 2))
					.add(new Unit(quant, "pint",       "pt",    cuft * gal * 4 * 2))
					.add(new Unit(quant, "quart",      "qt",    cuft * gal * 4))
					.add(new Unit(quant, "gallon",     "gal",   cuft * gal))
					.add(new Unit(quant, "cubicInch",  "cu in", cuft * 1728))
					.add(new Unit(quant, "cubicFoot",  "cu ft", cuft))
					.add(new Unit(quant, "milliliter", "ml",    1000.0 * 1000))
					.add(new Unit(quant, "liter",      "l",     1000))
					.add(new Unit(quant, "cubicMeter", "cu m",  1)));
			}
			catch (Exception ex) {
				Log.e(Calculators.TAG, "exception", ex);
			}
		}

		return singleton;
	}
}
