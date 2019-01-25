package edu.byui.cit.creatures.view;

import android.support.annotation.NonNull;


/**
 * This class simply adds functionality to the Android Fragment class.
 * This added functionality is useful for fragments in this app.
 */
public class Fragment extends android.support.v4.app.Fragment {
	@NonNull
	public MainActivity getMainActivity() {
		return (MainActivity)getActivity();
	}
}
