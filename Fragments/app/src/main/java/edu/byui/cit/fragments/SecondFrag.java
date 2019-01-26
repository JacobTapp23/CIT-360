package edu.byui.cit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SecondFrag extends Fragment {
	private EditText txtPhone;
	private String phone;


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstState);
			view = inflater.inflate(R.layout.frag_second, container, false);

			txtPhone = view.findViewById(R.id.txtPhone);
			Button btnThird = view.findViewById(R.id.btnThird);
			btnThird.setOnClickListener(new HandleThird());
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
		return view;
	}


	@Override
	public void onPause() {
		try {
			this.phone = txtPhone.getText().toString();
			super.onPause();
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}

	}

	String getPhone() {
		return this.phone;
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
