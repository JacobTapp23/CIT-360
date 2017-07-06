import javax.swing.JOptionPane;
import java.io.PrintStream;

public class Selection {
	public static void main(String[] args) {
		PrintStream out = java.lang.System.out;

		out.println("A simple if statement");
		for (double balance : new double[]{700, 500}) {
			if (balance > 500) {
				double interest = balance * .03;
				balance += interest;
			}
			out.println("balance " + balance);
		}
		out.println();

		out.println("An if statement with a matching else");
		for (double sales : new double[]{900, 1250}) {
			double bonus;
			if (sales <= 1000) {
				bonus = 20;
			}
			else {
				bonus = 100;
			}
			double salary = sales * 0.10 + bonus;
			out.println("salary " + salary);
		}
		out.println();

		out.println("if ... else if ... else");
		for (double total : new double[]{70, 300, 480}) {
			double discount;
			if (total < 100) {
				discount = 0.10;
			}
			else if (total < 250) {
				discount = 0.15;
			}
			else if (total < 400) {
				discount = 0.18;
			}
			else {
				discount = 0.20;
			}
			total *= (1 - discount);
			out.println("total " + total);
		}
		out.println();

		out.println("switch");
		for (char classStanding : new char[]{'F', 'J', 'Q'}) {
			String registerDate;
			switch (classStanding) {
			case 'F':  registerDate = "Nov 21";  break;
			case 'S':  registerDate = "Nov 17";  break;
			case 'J':  registerDate = "Nov 12";  break;
			case 'N':  registerDate = "Nov 6";   break;
			default:
				registerDate = "none";
				JOptionPane.showMessageDialog(null,
						"unknown class standing");
				break;
			}
			out.println("registration date " + registerDate);
		}
		out.println();

		out.println("Even and odd integers");
		{
			String text = "8";
			int value = Integer.parseInt(text);
			if ((value % 2) == 0) {
				out.println(value + " is an even integer");
			}
			else {
				out.println(value + " is an odd integer");
			}
		}

		for (int value : new int[]{5, -9}) {
			// Wrong!  Doesn't work for negative odd integers!
			if ((value % 2) == 1) {
				out.println(value + " is odd");
			}
			else {
				out.println(value + " is even");
			}
		}

		{
			int value = -9;
			// Correct, check for odd integers
			if ((value % 2) != 0) {
				out.println(value + " is odd");
			}
		}

		{
			int value = 8;
			// Correct, but poor check for even integers!
			if ((value & 1) == 0) {
				out.println(value + " is even");
			}
		}

		{
			double a = 1, b = -4, c = 3;
			double discr = b * b - 4 * a * c;
			double root1, root2;
			if (discr >= 0) {
				double sq = Math.sqrt(discr);
				root1 = (-b + sq) / (2 * a);
				root2 = (-b - sq) / (2 * a);
				out.println("roots of  y = " + a + "x*x + " + b + "x + " + c +
						"  " + root1 + " " + root2);
			}
		}
		out.println();

		out.println("Other examples");
		{
			String input = "850";
			double sales = Double.parseDouble(input);
			double rate;
			if (sales < 300) {
				rate = 0;
			}
			else {
				if (sales < 600) {
					rate = 0.02;
				}
				else {
					if (sales < 1000) {
						rate = 0.025;
					}
					else {
						rate = 0.03;
					}
				}
			}
			double commission = sales * rate;
			out.println(
					"The employee earned " + commission + " in commission.");
		}

		{
			String input = "850";
			double sales = Double.parseDouble(input);
			double rate;
			if (sales < 300) {
				rate = 0;
			}
			else if (sales < 600) {
				rate = 0.02;
			}
			else if (sales < 1000) {
				rate = 0.025;
			}
			else {
				rate = 0.03;
			}
			double commission = sales * rate;
			out.println(
					"The employee earned " + commission + " in commission.");
		}

		{
			int age = 38, gamesAttended = 6;
			double price;
			if (age < 18) {
				if (gamesAttended > 10)
					price = 5.0;
				else if (gamesAttended > 5)
					price = 6.0;
				else
					price = 8.0;
			}
			else if (age < 55) {
				if (gamesAttended > 10)
					price = 8.0;
				else
					price = 10.0;
			}
			else {
				if (gamesAttended > 10)
					price = 6.0;
				else
					price = 8.0;
			}
			out.println("ticket price " + price);
		}

		{
			int x = 1, y = 5;
			String message = "Sunny";
			if (x < 2) {
				if (y > 8) {
					message = "Cloudy";
				}
			}
			else {
				message = "Rainy";
			}
			out.println(message);
		}
	}
}
