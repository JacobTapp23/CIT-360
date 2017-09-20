import java.io.*;
import java.util.ArrayList;

public class TextFileIO {
	/* In Java there are two categories of objects that can read from files.
	 * 1. Streams write to and read from binary files, such as .png or
	 * .jpg files.
	 * 2. Writers and Readers write to and read from text files.
	 *
	 * This program demonstrates how to write to and read from a text file.
	 */
	public static void main(String[] args) {
		// Make a local variable to print to the console. This is a
		// shortcut, so that I can type "out" instead of "System.out".
		PrintStream out = System.out;

		// Create a File object that refers to a local text file.
		File file = new File("out.txt");

		String[] text = {
				"We the People of the United States, in Order to form a more",
				"perfect Union, establish Justice, insure domestic Tranquility,",
				"provide for the common defence, promote the general Welfare, and",
				"secure the Blessings of Liberty to ourselves and our Posterity,",
				"do ordain and establish this Constitution for the United States",
				"of America."
		};
		try {
			// Write the text to a text file.
			writeText(file, text);

			// Read the text from the text file.
			String[] read = readText(file);

			// Print the text that was read from the text file.
			for (String line : read) {
				out.println(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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
		ArrayList<String> text = new ArrayList<>();

		BufferedReader br = null;
		FileReader fr = new FileReader(file);
		try {
			br = new BufferedReader(fr);

			// Read each line from the file and add each line to the ArrayList.
			String line;
			while ((line = br.readLine()) != null) {
				text.add(line);
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
		int size = text.size();
		String[] array = new String[size];
		return text.toArray(array);
	}
}
