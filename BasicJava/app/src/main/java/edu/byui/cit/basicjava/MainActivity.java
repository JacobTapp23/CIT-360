package edu.byui.cit.basicjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.OutputStream;
import java.io.PrintStream;


/**
 * A simple activity that contains a single TextView and enables plain old
 * Java applications that write to the console to work in an Android app.
 */
public class MainActivity extends Activity {
	public static final String TAG = "BasicJava";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			// Find the TextView and change System.out
			// so that it will print to the TextView.
			TextView console = findViewById(R.id.console);
			PrintStream out = new PrintStream(new TextViewWriter(console));
			System.setOut(out);
			System.setErr(out);
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

			// Call main in the plain old Java applications.
			FirstProgram.main(null);
			Arithmetic.main(null);
			Selection.main(null);
//			Repetition.main(null);
			Methods.main(null);
			Array.main(null);
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
	}
}
