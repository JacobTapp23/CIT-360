package edu.byui.cit.worktime.controller;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import java.util.List;

import edu.byui.cit.worktime.R;
import edu.byui.cit.worktime.model.AppDatabase;
import edu.byui.cit.worktime.model.Project;
import edu.byui.cit.worktime.model.ProjectDAO;


final class ProjectAdapter
        extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    // A reference to the main activity
    private final MainActivity activity;

    // A reference to the project DAO to read and write to the Project table.
    private final ProjectDAO pdao;

    // A list of all projects stored in the Project table. This is an in
    // memory copy of the projects that are stored in the Project table
    // of the Room database.
    private final List<Project> dataset;


    ProjectAdapter(MainActivity activity) {
        // Tell the RecyclerView that the project keys are stable.
        setHasStableIds(true);

        this.activity = activity;

        // Get a reference to the project DAO.
        Context appCtx = activity.getApplicationContext();
        AppDatabase db = AppDatabase.getInstance(appCtx);
        pdao = db.getProjectDAO();


        // Get a list of all the projects in the Project table.
        dataset = pdao.getAll();

    }

    @Override
    public int getItemCount() {
        // Return the number of elements that are
        // stored in the in memory list of projects.
        return dataset.size();

    }

    @Override
    public long getItemId(int index) {
        // Return the key of the project that is
        // stored in the list of projects at index.
        Project proj = dataset.get(index);
        return proj.getProjectKey();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        return new ViewHolder(view);
    }

    // This onBindViewHolder method will be called each time that the
    // WorkTime app displays a project in a row of the RecyclerView.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        try {
            holder.bind(index);
        } catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString());
        }
    }


    // Each ViewHolder object corresponds to one row in the RecyclerView.
    // Each ViewHolder object will hold three TextViews that display a
    // project to the user. The TextViews are defined in item_project.xml.
    final class ViewHolder extends RecyclerView.ViewHolder
            implements OnLongClickListener, OnMenuItemClickListener {

        // References to the three TextViews in this row.
        private final TextView txtStartDate, txtTitle, txtDuration;

        // A reference to the project object that
        // is displayed (held) in this ViewHolder.
        private Project heldProj;


        ViewHolder(View view) {
            super(view);

            // Get a reference to each of the three
            // TextViews that are in the corresponding row.
            txtStartDate = view.findViewById(R.id.txtStartDate);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtDuration = view.findViewById(R.id.txtDuration);

            view.setOnLongClickListener(this);
        }

        // Bind this ViewHolder to the project that is
        // stored at index in the list of all projects.
        void bind(int index) {
            heldProj = dataset.get(index);

            // Display in the txtTitle TextView
            // the data that is in heldProj.
            String title = heldProj.getTitle();
            txtTitle.setText(title);

        }

        // This onLongClick method will be called when the
        // user long presses one row in the RecyclerView.
        @Override
        public boolean onLongClick(View view) {
            try {
                // Create a popup menu and show it to the user.
                PopupMenu menu = new PopupMenu(activity, view);
                menu.getMenuInflater().inflate(R.menu.popup, menu.getMenu());
                menu.setOnMenuItemClickListener(this);
                menu.show();
            } catch (Exception ex) {
                Log.e(MainActivity.TAG, ex.toString());
            }
            return true;
        }

        // This onMenuItemClick method will be called when
        // the user presses an item on the popup menu.
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            boolean handled = false;
            try {
                if (item.getItemId() == R.id.itmDelete) {
                        // Delete the project that the user long
                        // pressed (heldProj) from the Project table.

                        pdao.delete(heldProj);



                        // Get the index of the project that
                        // the user long pressed (heldProj)
                        // from the in memory list of projects.

                        int index = dataset.indexOf(heldProj);

                        // remove heldProj from
                        // the in memory list of projects.
                        dataset.remove(index);


                        // Notify the RecyclerView that
                        // heldProj has been deleted.
                        notifyItemRemoved(index);
                        handled = true;
                }
            } catch (Exception ex) {
                Log.e(MainActivity.TAG, ex.toString());
            }
            return handled;
        }
    }
}