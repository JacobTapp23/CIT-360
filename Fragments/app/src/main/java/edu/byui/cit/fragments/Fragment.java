package edu.byui.cit.fragments;

import org.jetbrains.annotations.NotNull;


/**
 * This class simply adds functionality to the Android Fragment class.
 * This added functionality is useful for fragments in this app.
 */
public class Fragment extends android.support.v4.app.Fragment {
	@NotNull
	public MainActivity getMainActivity() {
		return (MainActivity)getActivity();
	}
}
