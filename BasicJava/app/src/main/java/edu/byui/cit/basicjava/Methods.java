package edu.byui.cit.basicjava;

import static java.lang.System.out;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Methods {
	public static void main(String[] args) {
		{	// Pseudo random integer between min and max
			int min = 5, max = 11;
			int i = min + (int)(Math.random() * (max - min));
			out.println(i);
		}

		{	// Pseudo random integer between min and max
			int min = 5, max = 11;

			// Should be executed only once per run of the program.
			Random rnd = new Random();

			// Executed each time another pseudo random number is needed.
			int i = min + rnd.nextInt(max - min);
			out.println(i);
		}

		try
		{	// Pseudo random integer between min and max
			int min = 5, max = 11;

			// Should be executed only once per run of the program.
			Random rnd = SecureRandom.getInstance("SHA1PRNG");

			// Executed each time another pseudo random number is needed.
			int i = min + rnd.nextInt(max - min);
			out.println(i);
		}
		catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		boolean result = isEven(8);
		out.println(8 + " is even: " + result);

		out.println(8 + " is even: " + isEven2(8));

		double r = triArea(4, 2, 5);
		out.println("triangle area " + r);

		double pa = pyramidSurfaceArea(8, 7);
		out.println(pa);

		int m = determineMonths(100, 0.06, 103);
		out.println(m);

		long x = -24;
		long y = 472;
		long divisor = gcd(x, y);
		out.println("The gcd of " + x + " and " + y + " is " + divisor);

		Random rand = new Random(56);
		for (int i = 0;  i < 5;  i++) {
			long a = rand.nextInt();
			long b = rand.nextInt();
			long euclid = gcd(a, b);
			out.println("The gcd of " + a + " and " + b + " is " + euclid);

			a /= 3;
			b /= 5;
			euclid = gcd(a, b);
			out.println("The gcd of " + a + " and " + b + " is " + euclid);

			a /= 11;
			b /= 7;
			euclid = gcd(a, b);
			out.println("The gcd of " + a + " and " + b + " is " + euclid);

			a = b * 3;
			b *= 5;
			euclid = gcd(a, b);
			out.println("The gcd of " + a + " and " + b + " is " + euclid);
		}

		Calendar cal = Calendar.getInstance();
		cal.set(1972, 7, 15);
		getDiscountRate(cal.getTime(), 3.81);
	}


	public static float average(float a, float b, float c) {
		float sum = a + b + c;
		float avg = sum / 3;
		return avg;
	}

	public static double squareArea(double length) {
		double area = length * length;
		return area;
	}

	public static double rectangleArea(double width, double length) {
		double area = width * length;
		return area;
	}

	public static boolean isEven(int value) {
		if ((value % 2) == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isEven2(int value) {
		return (value % 2) == 0;
	}


	public static double triArea(double a, double b, double c) {
		double s  = (a + b + c) / 2.0;
		double area = Math.sqrt(s * (s-a) * (s-b) * (s-c));
		return area;
	}

	/** Computes and returns the surface area of the
	 * four triangular faces of a regular pyramid
	 * with the specified base length and height. */
	public static double pyramidSurfaceArea(double base, double height) {
		double edge = Math.sqrt(base*base/2.0 + height*height);
		double triArea = triArea(base, edge, edge);
		double pyramidArea = 4 * triArea;
		return pyramidArea;
	}

	/** Computes and returns how many months are
	 * needed for principal invested at a constant
	 * annual rate to grow to a target amount. */
	public static int determineMonths(
			double principal, double annualRate, double target) {
		double monthlyRate = annualRate / 12;
		double balance = principal;
		int month = 0;

		// Repeat while the balance of the
		// investment is less than the target.
		while (balance < target) {
			double interest = balance * monthlyRate;
			balance += interest;
			month++;
		}

		return month;
	}

	/** Uses Euclid's algorithm to find the
	 * greatest common divisor of two integers. */
	public static long gcd(long a, long b) {
		// Ensure a and b are not negative.
		if (a < 0)
			a = -a;
		if (b < 0)
			b = -b;

		// Ensure a is greater than or equal to b.
		if (a < b) {
			long swap = a;
			a = b;
			b = swap;
		}

		// Loop until the greatest common divisor is found.
		while (true) {
			a %= b;
			if (a == 0)
				return b;
			b %= a;
			if (b == 0)
				return a;
		}
	}


	public static double getDiscountRate(Date birthday, double gpa) {
		Calendar cal = Calendar.getInstance();

		// Wrong! Don't re-declare nor assign parameters.
		// cal.set(1995, 3, 17);
		// Date birthday = cal.getTime();
		// double gpa = window.prompt("What is your GPA?");

		cal.setTime(birthday);
		int birthYear = cal.get(Calendar.YEAR);
		cal.setTime(new Date());
		int currYear = cal.get(Calendar.YEAR);
		int age = currYear - birthYear;
		out.println(age);
		double rate = 0;
		//		&hellip;
		return rate;
	}
}
