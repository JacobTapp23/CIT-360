package edu.byui.cit.creatures.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public final class BothFrag extends Fragment {
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.frag_both, container, false);
	}
}
