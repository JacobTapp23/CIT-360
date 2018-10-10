package edu.byui.cit.console;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.io.PrintStream;


public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		/* Write your code in this function as if this function were
		 * public static void main(String[] args)
		 */
		System.out.println("Elephants are big.");
		System.out.println("Spaceships are expensive.");
	}
}
