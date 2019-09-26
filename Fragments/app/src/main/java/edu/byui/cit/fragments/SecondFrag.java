package edu.byui.cit.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SecondFrag extends Fragment {
	private EditText txtPhone;
	private TextView txtResponse;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstState);
			view = inflater.inflate(R.layout.frag_second, container, false);

			// Change the button that appears at the start of the
			// toolbar from the menu icon to the home as up indicator.
			MainActivity act = getMainActivity();
			act.setDrawerIndicatorEnabled(false);

			txtPhone = view.findViewById(R.id.txtPhone);
			txtResponse = view.findViewById(R.id.txtResponse);

			Button btnThird = view.findViewById(R.id.btnThird);
			btnThird.setOnClickListener(new HandleThird());

			txtPhone.requestFocus();
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
			act.setTitle(R.string.second);
			txtResponse.setText(act.getResponse());
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
	}


	@Override
	public void onPause() {
		try {
			String phone = txtPhone.getText().toString().trim();
			getMainActivity().setPhone(phone);
			super.onPause();
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
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
				MainActivity act = getMainActivity();
				Resources res = act.getResources();
				String[] messages = res.getStringArray(R.array.messages);
				args.putString("message", choose(messages));
				third.setArguments(args);

				// Switch to the third fragment.
				act.switchToFragment(third, "third");
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}

	private static String choose(String[] a) {
		int r = (int)Math.floor(Math.random() * a.length);
		return a[r];
	}
}
