package edu.byui.cit.exceptions;

import java.io.PrintStream;


/**
 * Demonstrates Java exceptions. Exceptions are
 * used to handle errors in an organized way.
 */
public class Exceptions {
	public static void main(String[] args) {
		PrintStream out = System.out;

		try {
			/* There are two types of statements that should be placed inside
			 * a try block:
			 * 1. Statements that might cause an exception to be thrown.
			 * 2. Statements that are dependent on the statements in the
			 * first category.
			 */
			String[] familyNames = { "Cooper", "Diaz" };
			String[] givenNames = { "James", "Cynthia", "Ignacio" };
			for (String family : familyNames) {
				for (String given : givenNames) {
					String full = makeFullName(given, family);
					String given2 = extractGivenName(full);
					String family2 = extractFamilyName(full);
					out.println(given + " " + family + "; " + full
							+ "; " + given2 + " " + family2);
				}
			}
			out.println();

			String given = null;
			String family = "Lovin";

			/* The makeFullName function may throw an exception, so the
			 * statement that calls makeFullName should be inside a try block.
			 */
			String full = makeFullName(given, family);

			/* The previous statement may cause an exception to be thrown.
			 * This println statement is dependent on the previous statement,
			 * so this println statement also should be inside a try block.
			 */
			out.println(full);
		}
		catch (IllegalArgumentException ex) {
			/* A single try block may have multiple catch blocks. The
			 * catch blocks must be ordered from most specific to most
			 * general. For example, IllegalArgumentException is more
			 * specific than Exception, so the catch block that catches
			 * IllegalArgumentException must come before the catch block for
			 * Exception.
			 */
			out.println("Error: " + ex.getMessage());
		}
		catch (Exception ex) {
			/* This catch block will catch all exceptions that are not caught
			 * by the above catch blocks.
			 */
			ex.printStackTrace();
		}
		finally {
			/* The finally block is useful for cleaning up or releasing
			 * resources. The finally block is used for releasing resources
			 * because if any code in a try block is executed, the code in
			 * the matching finally block will always be executed regardless of
			 * whether an exception is thrown or not and regardless of
			 * whether a thrown exception is caught or not. This particular
			 * example doesn't have any resources that need to be released,
			 * so this finally block is empty.
			 */
		}
	}


	public static String makeFullName(String given, String family) {
		if (given == null || given.length() == 0) {
			throw new IllegalArgumentException("invalid first name: " + given);
		}
		if (family == null || family.length() == 0) {
			throw new IllegalArgumentException(
					"invalid family name: " + family);
		}
		return family + ", " + given;
	}


	public static String extractFamilyName(String full) {
		if (full == null || full.length() == 0) {
			throw new IllegalArgumentException("invalid full name: " + full);
		}
		int comma = full.indexOf(", ");
		if (comma == -1 || comma == 0) {
			throw new IllegalArgumentException("invalid full name: " + full);
		}
		String family = full.substring(0, comma);
		return family;
	}


	public static String extractGivenName(String full) {
		if (full == null || full.length() == 0) {
			throw new IllegalArgumentException("invalid full name: " + full);
		}
		int comma = full.indexOf(", ");
		if (comma == -1 || comma == 0) {
			throw new IllegalArgumentException("invalid full name: " + full);
		}
		String given = full.substring(comma + 2);
		if (given.length() == 0) {
			throw new IllegalArgumentException("invalid full name: " + full);
		}
		return given;
	}
}
