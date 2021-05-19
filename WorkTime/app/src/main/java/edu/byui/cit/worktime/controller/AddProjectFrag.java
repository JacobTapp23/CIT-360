package edu.byui.cit.worktime.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.byui.cit.worktime.R;
import edu.byui.cit.worktime.model.AppDatabase;
import edu.byui.cit.worktime.model.Project;
import edu.byui.cit.worktime.model.ProjectDAO;

public final class AddProjectFrag extends androidx.fragment.app.Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstState) {
        View view = null;
        try {
            super.onCreateView(inflater, container, savedInstState);

            // Load the xml file that corresponds to this Java file.
            view = inflater.inflate(R.layout.frag_add_project, container, false);

            //Find the Cancel button and attach a click listener to it
            Button buttonCancel = view.findViewById(R.id.buttonCancel);
            buttonCancel.setOnClickListener(new MainFrag.CancelCultureClick());

            //Find the Add button and attach a click listener to it
            Button buttonAdd = view.findViewById(R.id.buttonAdd);
            buttonAdd.setOnClickListener(new MainFrag.AddClick());

        } catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
        return view;
    }

    private final class CancelCultureClick implements View.OnClickListener {
        @Override
        public void onClick(View button) {
            // Simulate the user pressing the BACK ARROW
            Activity act = getActivity();
            assert act != null;
            act.onBackPressed();
        }
    }

    private final class AddClick implements View.OnClickListener {
        @Override
        public void onClick(View button) {
            try {
                //Get the project title and description for the user.
                View view = getView();
                assert view != null;
                EditText txtHidTitle = view.findViewById(R.id.txtHidTitle);
                String title = txtHidTitle.getText().toString();

                EditText projDescrip = view.findViewById(R.id.projDescrip);
                String descrip = projDescrip.getText().toString();

                // Create a Project object
                Project newProj = new Project(title, descrip);

                //Connect to the database
                Activity act = getActivity();
                assert act != null;
                Context appCtx = act.getApplicationContext();
                AppDatabase db = AppDatabase.getInstance(appCtx);
                ProjectDAO pdao = db.getProjectDAO();

                //Insert the new project into the database
                pdao.insert(newProj);

                // Simulate the user pressing the ADD Button
                act.onBackPressed();
            } catch (Exception ex) {
                Log.e(MainActivity.TAG, ex.toString());
            }
        }
    }
}
