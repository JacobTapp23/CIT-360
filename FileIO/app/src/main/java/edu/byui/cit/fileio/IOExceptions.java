package edu.byui.cit.fileio;

import java.io.*;

/** Demonstrates several exceptions that may happen while trying to
 * write to and read from files.
 */
public class IOExceptions {
	public static void main(String[] args) {
		PrintStream out = System.out;

		// Attempt to open and read from a text file that doesn't exist.
		try {
			String text = readTextFile();
			out.println(text);
		}
		catch (FileNotFoundException ex) {
			out.println("Error: " + ex.getClass().getName()
					+ " " + ex.getMessage());
		}
		catch (IOException ex) {
			out.println(ex.getMessage());
		}

		// Attempt to open many files but not close any of them.
		try {
			openManyFiles();
		}
		catch (IOException ex) {
			out.println("Error: " + ex.getClass().getName()
					+ " " + ex.getMessage());
		}

	}


	public static String readTextFile() throws IOException {
		File file = new File("somefile.txt");
		String str = null;

		// Attempt to open a file that doesn't exist.
		FileReader fr = new FileReader(file);
		try {
			// Read the contents of the file into one large string. Of
			// course, this code will not be executed because the file
			// doesn't exist.
			long size = file.length();
			char[] array = new char[(int)size];
			fr.read(array);
			str = new String(array);
		}
		finally {
			fr.close();
		}
		return str;
	}


	public static void openManyFiles() throws IOException {
		PrintStream out = System.out;

		// Open many files but don't close any of them. It is a mistake
		// to not close files.
		int count = 0;
		while (count < 100) {
			String filename = "file" + count + ".txt";
			File file = new File(filename);
			FileWriter fw = new FileWriter(file);
			count++;
		}
		out.println("Opened " + count + " files.");

		// Delete the files.
		for (int i = 0;  i < count;  ++i) {
			String filename = "file" + i + ".txt";
			File file = new File(filename);

			// Attempting to delete a file that is still open will fail.
			boolean success = file.delete();
			if (!success) {
				out.println("can't delete " + file.getName());
			}

			// It is possible that the Java Virtual Machine will be able
			// to delete the file after this program quits.
			file.deleteOnExit();
		}
	}
}
