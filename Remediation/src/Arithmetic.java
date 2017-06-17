import java.io.PrintStream;

public class Arithmetic {
	public static void main(String[] args) {
		// Make it possible to type out.println instead of System.out.println
		PrintStream out = java.lang.System.out;

		{
			out.println("Declaring and assigning variables.");
			int a = 8, b = -3;
			int swap = a;
			a = b;
			b = swap;
			out.println(a + ", " + b);
			out.println();
		}

		{
			out.println("Integer division and floating point division.");
			int a = 3, b = 4;
			out.println(a + " / " + b + " = " + (a / b));

			a = -9; b = 2;
			out.println(a + " / " + b + " = " + (a / b));

			double c = 9, d = 2;
			out.println(c + " / " + d + " = " + (c / d));
			out.println();
		}

		{
			out.println("Modulus operator");
			int x = 7, y = 3;
			out.println(x + " % " + y + " = " + (x % y));
			x = 12; y = 3;
			out.println(x + " % " + y + " = " + (x % y));
			x = -6; y = 4;
			out.println(x + " % " + y + " = " + (x % y));
			x = 6; y = -4;
			out.println(x + " % " + y + " = " + (x % y));
			x = -6; y = -4;
			out.println(x + " % " + y + " = " + (x % y));
			out.println();
		}

		{
			out.println("Converting strings to numbers");
			String text = "-23";
			byte   b = Byte.parseByte(text);
			short  s = Short.parseShort(text);
			int    i = Integer.parseInt(text);
			long   n = Long.parseLong(text);
			float  f = Float.parseFloat(text);
			double d = Double.parseDouble(text);
			out.println(text + " "
					+ b + " " + s + " " + i + " " + n + " " + f + " " + d);

			text = "-23.761"; // Causes parseInt to throw an exception
			try {
				i = Integer.parseInt(text);
				out.println(text + " " + i);
			}
			catch (Exception ex) {
				out.println(ex.getClass() + ": " + ex.getMessage());
			}

			f = Float.parseFloat(text);
			d = Double.parseDouble(text);
			out.println(text + " " + f + " " + d);
			out.println();
		}

		{
			out.println("Assignment (+=, -=, *=, etc) operators");
			double subtotal = 175;
			double tax = subtotal * 0.06;
			double total = subtotal + tax;
			out.println("total " + total);
		}

		{
			double total = 175;
			double tax = total * 0.06;
			total += tax;
			out.println("total " + total);
		}

		{
			double total = 175;
			total *= 1.06;
			out.println("total " + total);
			out.println();
		}

		{
			out.println("Increment and decrement operators");
			int x = 3;
			int y = ++x * 2 + 5;
			out.println(y);
		}

		{
			int x = 3;
			int y = x++ * 2 + 5;
			out.println(y);
		}

		{
			int q = 7;
			int s = 4 + 18 / --q;
			out.println(s);
			out.println();
		}

		{
			out.println("Other examples");
			double celsius = 20;
			double fahren = celsius * 9.0 / 5.0 + 32;
			out.println(celsius + " celsius is " + fahren + " fahrenheit");
		}

		{
			double fahren = 25;
			double celsius = 5.0 / 9.0 * (fahren - 32);
			out.println(fahren + " fahrenheit is " + celsius + " celsius");
		}

		{
			double r = 2.7, h = 5;
			double v = Math.PI * r * r * h;
			out.println("volume of a cylinder " + v);
		}

		{
			double x1 = 2, y1 = 5, x2 = 3, y2 = -2;
			double dx = x1 - x2;
			double dy = y1 - y2;
			double dist = Math.sqrt(dx * dx + dy + dy);
			out.println("distance " + dist);
		}
	}
}
