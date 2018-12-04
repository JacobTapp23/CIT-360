package edu.byui.cit.japanesecreatures;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFrag extends Fragment {
	static final String TAG = "MainFrag";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.frag_main, container, false);

		int[] buttonIds = { R.id.btnRoom, R.id.btnFirebase, R.id.btnBoth };
		MainActivity act = (MainActivity)getActivity();
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
}
