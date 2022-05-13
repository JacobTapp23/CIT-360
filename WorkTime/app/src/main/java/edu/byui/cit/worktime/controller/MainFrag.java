package edu.byui.cit.worktime.controller;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.byui.cit.worktime.R;


public final class MainFrag extends JacksFrag {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            super.onCreateView(inflater, container, savedInstState);

            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_main, container, false);

            MainActivity act = getMainActivity();
            RecyclerView recycler = view.findViewById(R.id.recycler);
            recycler.setLayoutManager(new LinearLayoutManager(act));


            ProjectAdapter adapter = new ProjectAdapter(act);
            recycler.setAdapter(adapter);

            // Get the floating action button and add a function to it.
            FloatingActionButton fabAddProject = view.findViewById(R.id.fabAddProject);
            fabAddProject.setOnClickListener(new HandleFABClick());

        } catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    private class HandleFABClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            try {
                // Create a new Add Project Fragment and display
                // it to the user.
                MainActivity act = (MainActivity) getActivity();
                assert act != null;
                AddProjectFrag frag = new AddProjectFrag();

                FragmentTransaction trans =
                        act.getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.fragContainer, frag);

                // Add this fragment transaction to the back arrow stack,
                // so that pressing the back arrow will return to the main fragment
                trans.addToBackStack(null);
                trans.commit();
            } catch (Exception ex) {
                Log.e(MainActivity.TAG, ex.toString());
            }
        }
    }

}