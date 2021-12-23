package edu.byui.cit.image;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class MainFrag extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            super.onCreateView(inflater, container, savedInstState);

            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_main, container, false);
        }
        catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }
}
