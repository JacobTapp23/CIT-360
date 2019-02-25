package edu.byui.cit.fragments;

import org.jetbrains.annotations.NotNull;


/**
 * This class simply adds functionality to the Android Fragment class.
 * This added functionality is useful for fragments in this app.
 */
public class Fragment extends android.support.v4.app.Fragment {

	/** Returns the activity for this app cast as a MainActivity. */
	@NotNull
	public MainActivity getMainActivity() {
		// Get the activity then cast it to a MainActivity. We know that
		// this cast will succeed because the activity for this app is
		// of type MainActivity.
		return (MainActivity)getActivity();
	}
}
