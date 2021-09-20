package edu.byui.cit.fragments;


import android.app.Activity;


/**
 * This class simply adds functionality to the Android Fragment class.
 * This added functionality is useful for fragments in this app.
 */
public class Fragment extends androidx.fragment.app.Fragment {

	/** Returns the activity for this app cast as a MainActivity. */
	public MainActivity getMainActivity() {
		// Get the activity then cast it to a MainActivity. We know that
		// this cast will succeed because the activity for this app is
		// of type MainActivity.
		Activity activity = getActivity();
		assert  activity != null;
		return (MainActivity)activity;
	}
}
