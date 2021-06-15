package edu.byui.cit.console;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * A simple activity that contains a single TextView and enables plain old
 * Java applications that write to the console to work in an Android app.
 */
public class MainActivity extends AppCompatActivity {
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
		System.out.println("Elephants are big.");
		System.out.println("Spaceships are expensive.");
	}
}
