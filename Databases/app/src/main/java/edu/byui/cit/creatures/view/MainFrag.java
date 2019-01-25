package edu.byui.cit.creatures.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public final class MainFrag extends Fragment {
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.frag_main, container, false);

		// Display the username in the username text field.
		MainActivity act = getMainActivity();
		EditText txtUsername = view.findViewById(R.id.txtUsername);
		txtUsername.setText(act.getUsername());
		txtUsername.setOnFocusChangeListener(new HandleUsername());

		// Add an action listener to each button.
		int[] buttonIds = { R.id.btnRoom, R.id.btnFirebase, R.id.btnBoth };
		View.OnClickListener[] listeners = {
				act.roomClickHandler,
				act.firebaseClickHandler,
				act.bothClickHandler
		};
		for (int i = 0; i < buttonIds.length; ++i) {
			Button button = view.findViewById(buttonIds[i]);
			button.setOnClickListener(listeners[i]);
		}

		return view;
	}


	private final class HandleUsername implements View.OnFocusChangeListener {
		@Override
		public void onFocusChange(View view, boolean hasFocus) {
			try {
				if (!hasFocus) {
					MainActivity act = getMainActivity();
					EditText txtUsername = (EditText)view;
					String username = txtUsername.getText().toString().trim();
					act.setUsername(username);
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}
}
