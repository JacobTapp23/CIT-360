package edu.byui.cit.fileio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		TextView console = findViewById(R.id.console);
		System.setOut(new PrintStream(new TextViewWriter(console)));
	}

	private static final class TextViewWriter extends OutputStream {
		private final StringBuilder buffer;
		private final TextView console;

		TextViewWriter(TextView console) {
			this.buffer = new StringBuilder();
			this.console = console;
		}

		@Override
		public void write(int b) {
			buffer.append(b);
			console.setText(buffer);
		}

		@Override
		public void write(@NotNull byte[] b, int offs, int len) {
			buffer.append(new String(b, offs, len));
			console.setText(buffer);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		main();
	}


	private void main() {
		/* In Java there are two categories of objects that can read from files.
		 * 1. Streams write to and read from binary files, such as .png or
		 * .jpg files.
		 * 2. Writers and Readers write to and read from text files.
		 *
		 * This program demonstrates how to write to and read from a text file.
		 */

		try {
			// Read an existing file from the assets folder.
			String[] read = readFromAssets("declaration.txt");

			// Print the text that was read from the text file.
			for (String line : read) {
				System.out.println(line);
			}

			// Write a text file to internal storage. Then
			// read that same file from internal storage.
			String filename = "preamble.txt";
			String[] text = {
					"We the People of the United States, in Order to form a more",
					"perfect Union, establish Justice, insure domestic Tranquility,",
					"provide for the common defence, promote the general Welfare,",
					"and secure the Blessings of Liberty to ourselves and our",
					"Posterity, do ordain and establish this Constitution for the",
					"United States of America."
			};

			// Create a File object that refers to a local text file.
			Context ctx = getApplicationContext();
			File dir = ctx.getFilesDir();
			File file = new File(dir, filename);

			// Write the text to a text file.
			writeText(file, text);

			// Read the text from the text file.
			read = readText(file);

			// Print the text that was read from the text file.
			for (String line : read) {
				System.out.println(line);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public String[] readFromAssets(String filename) throws IOException {
		/* When reading from a file, it is common to wrap reader objects
		 * inside other reader objects. In this example, the InputStream
		 * reads bytes from the file. The InputStreamReader combines bytes
		 * into text characters. The BufferedReader reads the characters
		 * one line at a time.
		 */

		// Create an ArrayList to hold the lines of text from the file.
		ArrayList<String> list = new ArrayList<>();

		BufferedReader br = null;
		InputStreamReader ir = null;
		InputStream is = getAssets().open(filename);
		try {
			ir = new InputStreamReader(is);
			br = new BufferedReader(ir);

			// Read each line from the file and add each line to the ArrayList.
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		}
		finally {
			// Close the outermost reader that was successfully opened.
			if (br != null) {
				br.close();
			}
			else if (ir != null) {
				ir.close();
			}
			else {
				is.close();
			}
		}

		// Create an array large enough to hold all the lines of text
		// from the text file. Copy the text from the ArrayList into the
		// array and return the array.
		int size = list.size();
		String[] array = new String[size];
		return list.toArray(array);
	}


	public static void writeText(File file, String[] text) throws IOException {
		/* When writing to a file, it is common to wrap writer objects
		 * inside other writer objects. In this example, the PrintWriter
		 * object contains the method named println which automatically
		 * adds a newline character at the end of each line. The
		 * BufferedWriter object maintains an in memory buffer. The
		 * FileWriter object actually writes characters to a file. These
		 * three objects work together like this:
		 *
		 * 1. When the computer executes pw.println(line), the
		 * PrintWriter object sends the characters in the line string
		 * and a newline character to the BufferedWriter object.
		 *
		 * 2. The BufferedWriter stores the characters in its buffer.
		 * When that buffer is full, the BufferedWriter object sends all
		 * the characters in the buffer to the FileWriter object.
		 *
		 * 3. The FileWriter object writes all the characters in the
		 * buffer to the file on the hard drive.
		 *
		 * Using a buffer is more time efficient than forcing the
		 * FileWriter to write characters one at a time.
		 */
		PrintWriter pw = null;
		BufferedWriter bw = null;
		FileWriter fw = new FileWriter(file);
		try {
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			for (String line : text) {
				pw.println(line);
			}
		}
		finally {
			/* If the computer executes any line in the matching try
			 * block, then the code in this finally block will be
			 * executed regardless of whether an exception is thrown or
			 * not and regardless of whether a thrown exception is
			 * caught or not. This means that we can write the code to
			 * close the file in this finally block. If the computer
			 * successfully opens the file, then the computer is
			 * guaranteed to execute this code which will close the
			 * file.
			 *
			 * When Writers are wrapped inside of other Writers, we
			 * should close only the outermost Writer. This is because
			 * the close method for the outermost Writer will call the
			 * close method for the Writer wrapped inside of it. That
			 * inner Writer will call the close method for the Writer
			 * wrapped inside it, and so on.
			 *
			 * This set of if statements determines which is the
			 * outermost Writer that was successfully opened and closes
			 * just that outermost Writer.
			 */
			if (pw != null) {
				pw.close();
			}
			else if (bw != null) {
				bw.close();
			}
			else {
				fw.close();
			}
		}
	}


	public static String[] readText(File file) throws IOException {
		// Create an ArrayList to hold the lines of text from the file.
		ArrayList<String> list = new ArrayList<>();

		BufferedReader br = null;
		FileReader fr = new FileReader(file);
		try {
			br = new BufferedReader(fr);

			// Read each line from the file and add each line to the ArrayList.
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		}
		finally {
			// Close the outermost reader that was successfully opened.
			if (br != null) {
				br.close();
			}
			else {
				fr.close();
			}
		}

		// Create an array large enough to hold all the lines of text
		// from the text file. Copy the text from the ArrayList into the
		// array and return the array.
		int size = list.size();
		String[] array = new String[size];
		return list.toArray(array);
	}
}
