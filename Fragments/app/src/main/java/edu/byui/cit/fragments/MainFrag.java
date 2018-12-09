package edu.byui.cit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFrag extends Fragment {
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.frag_main, container, false);

		Button btnSecond = view.findViewById(R.id.btnSecond);
		btnSecond.setOnClickListener(new HandleSecond());
		Button btnThird = view.findViewById(R.id.btnThird);
		btnThird.setOnClickListener(new HandleThird());

		return view;
	}


	private final class HandleSecond implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				MainActivity act = (MainActivity)getActivity();
				act.switchToSecondFrag();
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
				MainActivity act = (MainActivity)getActivity();
				act.switchToThirdFrag();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}
}
