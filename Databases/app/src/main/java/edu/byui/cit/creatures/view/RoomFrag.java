package edu.byui.cit.creatures.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import edu.byui.cit.creatures.roomModel.AppDatabase;
import edu.byui.cit.creatures.roomModel.Creature;
import edu.byui.cit.creatures.roomModel.CreatureDAO;


public final class RoomFrag extends Fragment {
	private TextView txtCreatureID;
	private EditText txtName, txtType;
	private Button[] notInsertButtons;

	private CreatureDAO dao;

	/** A list of all creatures that are stored in the Room Creature table. */
	private List<Creature> creatureList;

	private int index = -1;

	private enum State {Browsing, Inputting}

	private State state;


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
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
		notInsertButtons = new Button[]{ btnPrev, btnNext, btnUpdate,
				btnDeleteAll };

		// Connect to the Room database and get the
		// data access object for the Creature table.
		Activity act = getActivity();
		if (act != null) {
			Context appCtx = act.getApplicationContext();
			AppDatabase db = AppDatabase.getInstance(appCtx);
			dao = db.getCreatureDAO();
		}

		// Add two test creatures to the Creature table.
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

		state = State.Browsing;
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
				if (state == State.Browsing) {
					// Prepare the user interface for the user to enter data.
					clearFields();
					enableButtons(false);
					txtName.requestFocus();
					state = State.Inputting;
				}
				else {
					// The user has finished entering data, now insert a
					// Creature in the Creature table and change the state
					// to Browsing.
					String name = txtName.getText().toString().trim();
					String type = txtType.getText().toString().trim();
					dao.insert(new Creature(name, type));

					creatureList = dao.getAll();
					index = creatureList.size() - 1;
					showCreature();
					enableButtons(true);
					state = State.Browsing;
				}
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
				dao.update(new Creature(creatureID, name, type));

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
				if (state == State.Inputting) {
					// The user cancelled an insert.
					index = creatureList.size() - 1;
					enableButtons(true);
					state = State.Browsing;
				}
				else {
					// The user wants to delete an existing
					// row from the Creature table.
					String text = txtCreatureID.getText().toString();
					long creatureID = Long.parseLong(text);
					dao.delete(new Creature(creatureID));

					creatureList = dao.getAll();
					int last = creatureList.size() - 1;
					if (index > last) {
						index = last;
					}
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
				dao.deleteAll();

				creatureList = dao.getAll();
				index = creatureList.size() - 1;
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
			txtCreatureID.setText(Long.toString(creature.getKey()));
			txtName.setText(creature.getName());
			txtType.setText(creature.getType());
		}
		else {
			clearFields();
		}
	}

	private void enableButtons(boolean value) {
		for (Button button : notInsertButtons) {
			button.setEnabled(value);
		}
	}

	private void clearFields() {
		txtCreatureID.setText("");
		txtName.setText("");
		txtType.setText("");
	}
}
