package edu.byui.cit.creatures.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.byui.cit.creatures.firebaseModel.Creature;


public final class FirebaseFrag extends Fragment {
	private TextView txtCreatureKey;
	private EditText txtName, txtType;
	private Button[] notInsertButtons;

	private DatabaseReference dbCreatures;

	/**
	 * A list of all creatures that are stored in the firebase database. This
	 * is an in memory copy of the creatures that are in the firebase
	 * database.
	 */
	private List<Creature> listCreatures;

	private int index = -1;

	private enum State {Browsing, Inputting, PendingInsert}

	private State state;
	private String pendingKey;


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.frag_firebase, container, false);

		txtCreatureKey = view.findViewById(R.id.txtCreatureKey);
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

		if (dbCreatures == null) {
			listCreatures = new ArrayList<>();
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			dbCreatures = database.getReference("/creatures");
			dbCreatures.addChildEventListener(new CreatureEventHandler());
		}

		// Add two test creatures to the firebase database.
		MainActivity act = getMainActivity();
		Creature[] testCreatures = {
				new Creature("charizard", "fire", act.getUsername()),
				new Creature("squirtle", "water", act.getUsername())
		};
		for (Creature creature : testCreatures) {
			dbCreatures.push().setValue(creature);
		}

		pendingKey = null;
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
				int last = listCreatures.size() - 1;
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
					pendingKey = null;
					state = State.Inputting;
				}
				else {
					// The user has finished entering data, now insert a
					// Creature in the firebase database and change the state
					// to Browsing.
					String name = txtName.getText().toString().trim();
					String type = txtType.getText().toString().trim();
					String creator = getMainActivity().getUsername();
					DatabaseReference node = dbCreatures.push();
					node.setValue(new Creature(name, type, creator));
					pendingKey = node.getKey();
					state = State.PendingInsert;
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
				String key = txtCreatureKey.getText().toString();
				int index = listCreatures.indexOf(new Creature(key));
				Creature old = listCreatures.get(index);
				String name = txtName.getText().toString().trim();
				String type = txtType.getText().toString().trim();
				String updater = getMainActivity().getUsername();
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference toUpdate =
						database.getReference("/creatures/" + key);
				toUpdate.setValue(new Creature(name, type,
						old.getWhenCreated(), old.getCreator(), updater));
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
					index = listCreatures.size() - 1;
					enableButtons(true);
					pendingKey = null;
					state = State.Browsing;
					showCreature();
				}
				else {
					// The user wants to delete an existing
					// Creature from the firebase database.
					String key = txtCreatureKey.getText().toString();
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference toDelete =
							database.getReference("/creatures/" + key);
					toDelete.removeValue();
				}
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
				dbCreatures.removeValue();
				index = -1;
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private final class CreatureEventHandler implements ChildEventListener {
		/**
		 * Handles the event that firebase generates
		 * when a new creature is added to the database.
		 */
		@Override
		public void onChildAdded(
				@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
			try {
				// Get the creature that was added.
				Creature added = dataSnapshot.getValue(Creature.class);
				if (added != null) {
					String key = dataSnapshot.getKey();
					added.setKey(key);
					listCreatures.add(added);
					if (state == State.Browsing) {
						if (index == -1) {
							index = listCreatures.size() - 1;
							showCreature();
						}
					}
					else if (state == State.PendingInsert) {
						if (key != null && key.equals(pendingKey)) {
							index = listCreatures.size() - 1;
							showCreature();
							enableButtons(true);
							pendingKey = null;
							state = State.Browsing;
						}
					}
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, "1: " + ex.getMessage());
			}
		}

		/**
		 * Handles the event that firebase
		 * generates when a creature is updated.
		 */
		@Override
		public void onChildChanged(
				@NonNull DataSnapshot dataSnapshot, String s) {
			try {
				Creature changed = dataSnapshot.getValue(Creature.class);
				if (changed != null) {
					String key = dataSnapshot.getKey();
					changed.setKey(key);
					int i = listCreatures.indexOf(changed);
					listCreatures.set(i, changed);
					if (i == index) {
						showCreature();
					}
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, "2: " + ex.getMessage());
			}
		}

		/**
		 * Handles the event that firebase
		 * generates when a creature is deleted.
		 */
		@Override
		public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
			try {
				String key = dataSnapshot.getKey();
				int i = listCreatures.indexOf(new Creature(key));
				if (i != -1) {
					listCreatures.remove(i);
					if (i == index) {
						showCreature();
					}
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, "3: " + ex.getMessage());
			}
		}

		@Override
		public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String
				s) {
		}

		@Override
		public void onCancelled(DatabaseError error) {
			// Failed to read value
			Log.e(MainActivity.TAG, "4: DB error: " + error.toString());
		}
	}


	private void showCreature() {
		int size = listCreatures.size();
		if (index >= size) {
			index = size - 1;
		}
		if (0 <= index && index < listCreatures.size()) {
			Creature creature = listCreatures.get(index);
			txtCreatureKey.setText(creature.getKey());
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
		txtCreatureKey.setText("");
		txtName.setText("");
		txtType.setText("");
	}
}
