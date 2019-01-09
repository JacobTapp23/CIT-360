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
		super.onCreateView(inflater, container, savedInstState);
		View view = inflater.inflate(R.layout.frag_second, container, false);

		txtPhone = view.findViewById(R.id.txtPhone);
		Button btnThird = view.findViewById(R.id.btnThird);
		btnThird.setOnClickListener(new HandleThird());

		return view;
	}


	@Override
	public void onPause() {
		this.phone = txtPhone.getText().toString();
		super.onPause();
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
