package edu.byui.cit.room.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import edu.byui.cit.room.R;


/**
 * This is an example app that shows how to use the following:
 * 1. Fragments
 * 2. User preferences
 * 3. Room database
 */
public final class MainActivity extends AppCompatActivity {
	static final String TAG = "Creatures";
	private static final String USERNAME_KEY = "username";

	private String username;


	@Override
	protected void onCreate(Bundle savedInstState) {
		try {
			super.onCreate(savedInstState);
			Context ctx = getApplicationContext();
			setContentView(R.layout.activity_main);

			if (savedInstState == null) {
				// Create the main fragment and place it
				// as the first fragment in this activity.
				Fragment fragMain = new StartFrag();
				FragmentTransaction trans =
						getSupportFragmentManager().beginTransaction();
				trans.add(R.id.fragContainer, fragMain);
				trans.commit();
			}

		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
	}


	/** Displays a different fragment in the frag container. */
	void switchFragment(Fragment fragment) {
		// Replace whatever is in the fragContainer view with
		// fragment, and add the transaction to the back stack so
		// that the user can navigate back.
		FragmentTransaction trans = getSupportFragmentManager()
				.beginTransaction();
		trans.replace(R.id.fragContainer, fragment);
		trans.addToBackStack(null);
		trans.commit();
	}


	public String getUsername() {
		if (username == null) {
			SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
			username = prefs.getString(USERNAME_KEY, "");
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(USERNAME_KEY, username);
		editor.apply();
	}
}
