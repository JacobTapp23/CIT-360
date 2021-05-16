package edu.byui.cit.worktime.controller;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.byui.cit.worktime.R;


public final class MainFrag extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            super.onCreateView(inflater, container, savedInstState);

            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_main, container, false);

            // Get the floating action button and add a function to it.
            FloatingActionButton fabAddProject = view.findViewById(R.id.fabAddProject);
            fabAddProject.setOnClickListener(new HandleFABClick());
        }
        catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    private class HandleFABClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Context ctx = getActivity();
            Toast.makeText(ctx, "FAB Clicked!", Toast.LENGTH_LONG).show();
        }
    }

}