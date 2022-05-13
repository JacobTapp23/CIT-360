package edu.byui.cit.alarms;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import java.io.OutputStream;
import java.io.PrintStream;


/**
 * A simple activity that contains a single TextView and enables plain old
 * Java applications that write to the console to work in an Android app.
 */
public class MainActivity extends Activity {
	public static final String TAG = "Alarms";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			// Find the TextView and change System.out
			// so that it will print to the TextView.
			TextView console = findViewById(R.id.console);
			System.setOut(new PrintStream(new TextViewWriter(console)));

			setAlarm();
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


	private void setAlarm() {
		/* Write your code in this function as if this function were
		 * public static void main(String[] args)
		 */
		System.out.println("Elephants are big.");
		System.out.println("Spaceships are expensive.");

		Context ctx = this;
		AlarmManager alarmMgr = (AlarmManager)ctx.getSystemService(
				Context.ALARM_SERVICE);

		Intent intent = new Intent(ctx, AlarmReceiver.class);
		intent.setAction("edu.byui.cit.alarms");
		PendingIntent pending = PendingIntent.getBroadcast(ctx, 0, intent, 0);

		final long INTERVAL_30_SECONDS = 30 * 1000;
		final long INTERVAL_ONE_MINUTE = 60 * 1000;
		final long INTERVAL_FIVE_MINUTES = 5 * 60 * 1000;

		// Set an alarm that will fire in 30 seconds.
//		alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//				SystemClock.elapsedRealtime() + INTERVAL_30_SECONDS, pending);
//		System.out.println("Set an alarm to fire in 30 seconds.");

		// Set an alarm that will fire in 30 seconds and then again every
		// minute.
		alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime() + INTERVAL_30_SECONDS,
				INTERVAL_ONE_MINUTE, pending);
		System.out.println("Set a repeating alarm to fire every minute.");
	}
}
