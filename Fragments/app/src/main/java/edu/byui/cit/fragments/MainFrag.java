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
	private TextView phoneNumber;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstanceState);
			view = inflater.inflate(R.layout.frag_main, container, false);

			phoneNumber = view.findViewById(R.id.txtPhone);

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
			MainActivity act = getMainActivity();
			SecondFrag fragSecond = act.getSecondFrag();
			String phone = fragSecond.getPhone();
			phoneNumber.setText(phone);
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
	}


	private final class HandleSecond implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				MainActivity act = getMainActivity();
				act.switchToFragment(act.getSecondFrag());
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
				MainActivity act = getMainActivity();
				act.switchToFragment(act.getThirdFrag());
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}
}
