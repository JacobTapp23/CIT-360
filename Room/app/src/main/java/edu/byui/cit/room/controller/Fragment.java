package edu.byui.cit.room.controller;

import android.app.Activity;

import androidx.annotation.NonNull;


/**
 * This class simply adds functionality to the Android Fragment class.
 * This added functionality is useful for fragments in this app.
 */
public class Fragment extends androidx.fragment.app.Fragment {
	@NonNull
	public MainActivity getMainActivity() {
		Activity activity = getActivity();
		assert activity != null;
		return (MainActivity)activity;
	}
}
