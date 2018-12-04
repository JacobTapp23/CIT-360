package edu.byui.cit.japanesecreatures;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import edu.byui.cit.roomModel.AppDatabase;
import edu.byui.cit.roomModel.Creature;
import edu.byui.cit.roomModel.CreatureDAO;


public class RoomFrag extends Fragment {
	static final String TAG = "RoomFrag";

	private TextView txtCreatureID;
	private EditText txtName, txtType;

	/** A list of all creatures that are stored in the Room database. */
	private List<Creature> creatureList;

	private int index = -1;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.frag_room, container, false);

		txtCreatureID = view.findViewById(R.id.txtCreatureID);
		txtName = view.findViewById(R.id.txtName);
		txtType = view.findViewById(R.id.txtType);

		Button btnPrev = view.findViewById(R.id.btnPrev);
		Button btnNext = view.findViewById(R.id.btnNext);
		Button btnInsert = view.findViewById(R.id.btnInsert);
		Button btnUpdate = view.findViewById(R.id.btnUpdate);
		Button btnDelete = view.findViewById(R.id.btnDelete);
		Button btnDeleteAll = view.findViewById(R.id.btnDeleteAll);
		btnPrev.setOnClickListener(new HandlePrev());
		btnNext.setOnClickListener(new HandleNext());
		btnInsert.setOnClickListener(new HandleInsert());
		btnUpdate.setOnClickListener(new HandleUpdate());
		btnDelete.setOnClickListener(new HandleDelete());
		btnDeleteAll.setOnClickListener(new HandleDeleteAll());

		Context appCtx = getActivity().getApplicationContext();
		AppDatabase db = AppDatabase.getInstance(appCtx);
		CreatureDAO dao = db.getCreatureDAO();

		// Add two test creatures to the Room database.
		Creature[] testCreatures = {
				new Creature("charizard", "fire"),
				new Creature("squirtle", "water")
		};
		for (Creature creature : testCreatures) {
			dao.insert(creature);
		}

		// Read all creatures from the Room database.
		creatureList = dao.getAll();
		if (creatureList.size() > 0) {
			index = 0;
			showCreature();
		}

		return view;
	}


	private final class HandlePrev implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				if (--index < 0) {
					index = 0;
				}
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private final class HandleNext implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				int last = creatureList.size() - 1;
				if (++index > last) {
					index = last;
				}
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private final class HandleInsert implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				String name = txtName.getText().toString().trim();
				String type = txtType.getText().toString().trim();
				Creature creature = new Creature(name, type);
				Context appCtx = getActivity().getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.insert(creature);

				creatureList = dao.getAll();
				index = creatureList.size() - 1;
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private final class HandleUpdate implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				String text = txtCreatureID.getText().toString();
				long creatureID = Long.parseLong(text);
				String name = txtName.getText().toString().trim();
				String type = txtType.getText().toString().trim();
				Creature creature = new Creature(creatureID, name, type);
				Context appCtx = getActivity().getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.update(creature);

				creatureList = dao.getAll();
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private final class HandleDelete implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				String text = txtCreatureID.getText().toString();
				long creatureID = Long.parseLong(text);
				Creature creature = new Creature(creatureID);
				Context appCtx = getActivity().getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.delete(creature);

				creatureList = dao.getAll();
				int last = creatureList.size() - 1;
				if (index > last) {
					index = last;
				}
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private final class HandleDeleteAll implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				Context appCtx = getActivity().getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.deleteAll();
				creatureList = dao.getAll();
				index = -1;
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private void showCreature() {
		if (0 <= index && index < creatureList.size()) {
			Creature creature = creatureList.get(index);
			txtCreatureID.setText(Long.toString(creature.getCreatureId()));
			txtName.setText(creature.getName());
			txtType.setText(creature.getType());
		}
		else {
			txtCreatureID.setText("");
			txtName.setText("");
			txtType.setText("");
		}
	}
}
