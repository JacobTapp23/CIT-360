package edu.byui.cit.basicjava;

import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class Repetition {
	public static void main(String[] args) {
		PrintStream out = java.lang.System.out;
		Scanner scanner = new Scanner(System.in);

		{	// While Loop
			int i = 1;
			while (i < 3) {
				out.println(i);
				i++;
			}
		}

		{	// For Loop
			for (int i = 1;  i < 3;  i++) {
				out.println(i);
			}
		}

		{
			int i = Integer.parseInt("4");
			for ( ;  i <= 5;  i++) {
				out.println(i);
			}
		}

		{	// do-while Loop
			String alias, resp;
			do {
				out.println("What is your name?");
				alias = scanner.next();
				out.println("Are you sure " + alias + " is your name?");
				resp = scanner.next();
			} while (!resp.equals("yes"));
		}

		{	// Zero based counting loop
			int n = 3;
			int i = 0;
			while (i < n) {
				out.println("leaf");
				i++;
			}
		}

		{	// Zero based counting loop
			int n = 3;
			for (int i = 0;  i < n;  i++) {
				out.println("caterpillar");
			}
		}

		{	// Skipping loop
			int n = 12;
			int skip = 4;
			int i = 0;
			while (i < n) {
				out.println("stem");
				i += skip;
			}
		}

		{	// Skipping loop
			int n = 5;
			int skip = 2;
			for (int i = 0;  i < n;  i += skip) {
				out.println("aphid");
			}
		}

		{  // Repetitive Strings
			String output = "0";
			int i = 2;
			while (i <= 10) {
				output += ", " + i;
				i += 2;
			}
			out.println(output);
		}

		{  // Repetitive Strings
			String output = "0";
			for (int i = 2;  i <= 10;  i += 2) {
				output += ", " + i;
			}
			out.println(output);
		}

		{	// Compound Interest
			out.println("Enter your account balance");
			double balance = scanner.nextDouble();
			out.println("Enter the annual interest rate");
			double annualRate = scanner.nextDouble();
			out.println("Enter the number of months");
			int numMonths = scanner.nextInt();
			double monthlyRate = annualRate / 12;
			for (int month = 1;  month <= numMonths;  month++) {
				double interest = balance * monthlyRate;
				interest = Math.rint(interest * 100) / 100.0;
				balance += interest;
			}
			out.println("Your balance after " +
					numMonths + " months will be $" + balance);
		}

		{	// Is Prime
			out.println("Please enter an integer greater than 1");
			int candidate = scanner.nextInt();

			// Number of factors that evenly divide candidate
			int factorCount = 0;

			for (int divisor = 1; divisor <= candidate;  ++divisor) {
				int remainder = candidate % divisor;
				if (remainder == 0) {
					factorCount++;
				}
			}
			if (factorCount == 2) {
				out.println(candidate + " is prime.");
			}
			else {
				out.println(candidate + " is not prime.");
			}
		}

		{	// Is Prime
			out.println("Please enter an integer greater than 1");
			int candidate = scanner.nextInt();
			boolean prime = true;
			int divisor = 2;
			int limit = (int)Math.floor(Math.sqrt(candidate));
			while (prime && divisor <= limit) {
				int remainder = candidate % divisor;
				prime = (remainder != 0);
				divisor++;
			}
			if (prime == true) {
				out.println(candidate + " is prime.");
			}
			else {
				out.println(candidate + " is not prime.");
			}
		}

		{	// Count down
			String c = "";
			out.println("Please enter an integer");
			int a = scanner.nextInt();
			int b = 100;
			while (b >= 0) {
				c += b + "\n";
				b -= a;
			}
			out.println(c);
		}

		{	// Count down
			String output = "";
			out.println("Please enter an integer");
			int skip = scanner.nextInt();
			int i = 100;
			while (i >= 0) {
				output += i + "\n";
				i -= skip;
			}
			out.println(output);
		}

		{	// Number guessing game
			String d = "Please enter an integer.";
			int b = 38;
			int c;
			do {
				out.println(d);
				c = scanner.nextInt();
				if (c < b) {
					d = "Too low.  Please enter another integer.";
				}
				else if (c > b) {
					d = "Too high.	Please enter another integer.";
				}
			} while (c != b);
			out.println("Yay!");
		}

		{	// Number guessing game
			String prompt =
					"I'm thinking of a number between 1 and 100.\n" +
							"Try to guess it!\n" +
							"Please enter an integer between 1 and 100.";
			int guess, answer = 38;
			do {
				out.println(prompt);
				guess = scanner.nextInt();
				if (guess < answer) {
					prompt = guess +
							" is too low.  Please enter another integer.";
				}
				else if (guess > answer) {
					prompt = guess +
							" is too high. Please enter another integer.";
				}
			} while (guess != answer);
			out.println(guess + " is correct!");
		}

		scanner.close();
	}


	// A function to read text from a file, one line at a time.
	public static void readFile(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String text;
		while ((text = br.readLine()) != null) {
			out.println(text);
		}
		br.close();
	}
}
