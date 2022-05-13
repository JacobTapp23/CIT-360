package edu.byui.cit.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;


public class AlarmReceiver extends BroadcastReceiver {
	private static final String TAG = AlarmReceiver.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Date now = new Date();
			DateFormat formatter = DateFormat.getDateTimeInstance();
			String text = "Received an alarm at " + formatter.format(now);
			Log.i(TAG, text);
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
			System.out.println(text);
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
	}
}
