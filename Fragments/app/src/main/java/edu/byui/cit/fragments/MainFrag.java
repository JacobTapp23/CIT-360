package edu.byui.cit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainFrag extends Fragment {
	private TextView txtPhone;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstanceState);
			view = inflater.inflate(R.layout.frag_main, container, false);

			// Change the button that appears at the start of the
			// toolbar from the home as up indicator to the menu icon.
			MainActivity act = getMainActivity();
			act.setDrawerIndicatorEnabled(true);

			txtPhone = view.findViewById(R.id.txtPhone);

			Button btnSecond = view.findViewById(R.id.btnSecond);
			btnSecond.setOnClickListener(new HandleSecond());
			Button btnThird = view.findViewById(R.id.btnThird);
			btnThird.setOnClickListener(new HandleThird());
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
		return view;
	}


	@Override
	public void onResume() {
		try {
			super.onResume();

			// If the MainActivity has the phone number, it came from the
			// second fragment. Get the phone number and display it for
			// the user to see.
			String phone = getMainActivity().getPhone();
			if (phone != null) {
				txtPhone.setText(phone);
			}
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
	}


	private final class HandleSecond implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				// Switch to the second fragment.
				MainActivity act = getMainActivity();
				act.switchToFragment(new SecondFrag());
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}

	private final class HandleThird implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				// Create the third fragment.
				ThirdFrag third = new ThirdFrag();

				// Create and populate an arguments
				// bundle for the third fragment.
				Bundle args = new Bundle();
				args.putString("message", "From Main");
				third.setArguments(args);

				// Switch to the third fragment.
				getMainActivity().switchToFragment(third);
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}
}
