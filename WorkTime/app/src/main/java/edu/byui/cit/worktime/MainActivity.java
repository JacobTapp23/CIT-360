package edu.byui.cit.worktime;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import edu.byui.cit.worktime.model.Project;
import edu.byui.cit.worktime.model.Session;


/**
 * A simple activity that contains a single TextView and enables plain old
 * Java applications that write to the console to work in an Android app.
 */
public class MainActivity extends Activity {
	public static final String TAG = "Console";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			// Find the TextView and change System.out
			// so that it will print to the TextView.
			TextView console = findViewById(R.id.console);
			System.setOut(new PrintStream(new TextViewWriter(console)));
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
	}

	private static final class TextViewWriter extends OutputStream {
		private final StringBuilder buffer;
		private final TextView console;

		TextViewWriter(TextView console) {
			this.buffer = new StringBuilder();
			this.console = console;
		}

		// Write a single byte to the console TextView.
		@Override
		public void write(int b) {
			buffer.append(b);
			console.setText(buffer);
		}

		// Write an array of bytes to the console TextView.
		@Override
		public void write(byte[] b, int offs, int len) {
			buffer.append(new String(b, offs, len));
			console.setText(buffer);
		}
	}


	@Override
	protected void onStart() {
		try {
			super.onStart();
			main();
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
	}

	private void main() {
		/* Write your code in this function as if this function were
		 * public static void main(String[] args)
		 */
		Project proj1 = new Project( "Defrost the freezer", "cold and frosty");
		Project proj2 = new Project( "Defrost the television", "picture is frozen");

		Date start1 = new Date(2021, 4, 23, 10,30);
		Date end1 = new Date(2021, 4, 23, 12, 30);
		Session s1 = new Session( proj1.getProjectKey(), "Unplug freezer to remove power",
		start1, end1);
		Session s2 = new Session( proj2.getProjectKey(), "Use remote control to turn up volume",
				start1, end1);
		Session s3 = new Session( proj1.getProjectKey(), "Grab icepick and hair dryer and lots of towels",
				start1, end1);
		System.out.println(proj1);
		System.out.println(proj2);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
	}
}
