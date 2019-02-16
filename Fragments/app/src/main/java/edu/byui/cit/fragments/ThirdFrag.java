package edu.byui.cit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ThirdFrag extends Fragment {
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstState);
			view = inflater.inflate(R.layout.frag_third, container, false);

			// Change the button that appears at the start of the
			// toolbar from the menu icon to the home as up indicator.
			MainActivity act = getMainActivity();
			act.setDrawerIndicatorEnabled(false);

			TextView txtMsg = view.findViewById(R.id.txtMsg);
			Bundle args = getArguments();
			String msg = args.getString("message");
			txtMsg.setText(msg);
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
		return view;
	}
}
