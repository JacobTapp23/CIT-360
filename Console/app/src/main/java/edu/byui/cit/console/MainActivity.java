package edu.byui.cit.console;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.OutputStream;
import java.io.PrintStream;


public class MainActivity extends Activity {
	public static final String TAG = "Console";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
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

		@Override
		public void write(int b) {
			buffer.append(b);
			console.setText(buffer);
		}

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
