package edu.byui.cit.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
			act.setResponse("");

			TextView txtMsg = view.findViewById(R.id.txtMsg);
			Bundle args = getArguments();
			String msg = args.getString("message");
			txtMsg.setText(msg);

			view.findViewById(R.id.btnHello).setOnClickListener(handleClick);
			view.findViewById(R.id.btnHowdy).setOnClickListener(handleClick);
			view.findViewById(R.id.btnYes).setOnClickListener(handleClick);
			view.findViewById(R.id.btnNo).setOnClickListener(handleClick);
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
			getMainActivity().setTitle(R.string.third);
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
	}


	private final View.OnClickListener handleClick = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			Button button = (Button)view;
			MainActivity act = getMainActivity();
			act.setResponse(button.getText().toString());
			act.onBackPressed();
		}
	};
}
