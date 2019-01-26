package edu.byui.cit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ThirdFrag extends Fragment {
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstState);
			view = inflater.inflate(R.layout.frag_third, container, false);
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
		return view;
	}
}
