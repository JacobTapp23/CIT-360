package edu.byui.cit.room.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import edu.byui.cit.room.R;


public final class MainFrag extends Fragment {
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstanceState);
			view = inflater.inflate(R.layout.frag_main, container, false);

			// Display the username in the username text field.
			MainActivity act = getMainActivity();
			EditText txtUsername = view.findViewById(R.id.txtUsername);
			txtUsername.setText(act.getUsername());
			txtUsername.setOnFocusChangeListener(new HandleUsername());

			// Add an action listener to each button.
			Button button = view.findViewById(R.id.btnRoom);
			button.setOnClickListener(new HandleRoomClick());
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
		return view;
	}


	private final class HandleUsername implements View.OnFocusChangeListener {
		@Override
		public void onFocusChange(View editText, boolean hasFocus) {
			try {
				if (!hasFocus) {
					EditText txtUsername = (EditText)editText;
					String username = txtUsername.getText().toString().trim();
					MainActivity act = getMainActivity();
					act.setUsername(username);
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.toString());
			}
		}
	}


	private final class HandleRoomClick implements View.OnClickListener {
		@Override
		public void onClick(View button) {
			MainActivity activity = getMainActivity();
			Fragment fragRoom = new RoomFrag();
			activity.switchFragment(fragRoom);
		}
	}
}
