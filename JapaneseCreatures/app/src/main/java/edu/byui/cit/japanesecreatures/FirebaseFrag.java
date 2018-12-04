package edu.byui.cit.japanesecreatures;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import edu.byui.cit.firebaseModel.Creature;


public class FirebaseFrag extends Fragment {
	static final String TAG = "FirebaseFrag";

	private TextView txtCreatureKey;
	private EditText txtName, txtType;
	private DatabaseReference dbCreatures;

	/** A list of all creatures that are stored in the firebase database. */
	private List<Creature> creatureList;

	private int index = -1;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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

		creatureList = new ArrayList<>();
		if (dbCreatures == null) {
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			dbCreatures = database.getReference("/creatures");
			dbCreatures.addChildEventListener(new CreatureAddedHandler());
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
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference creatures = database.getReference("/creatures");
				String name = txtName.getText().toString().trim();
				String type = txtType.getText().toString().trim();
				creatures.push().setValue(new Creature(name, type));
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
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference toUpdate = database.getReference("/creatures/" + key);
				String name = txtName.getText().toString().trim();
				String type = txtType.getText().toString().trim();
				toUpdate.setValue(new Creature(name, type));
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
				String key = txtCreatureKey.getText().toString();
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference toDelete = database.getReference("/creatures/" + key);
				toDelete.removeValue();
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
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference toDelete = database.getReference("/creatures");
				toDelete.removeValue();
				index = -1;
				showCreature();
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.getMessage());
			}
		}
	}


	private final class CreatureAddedHandler implements ChildEventListener {
		/** Handles the event that firebase generates
		 * when a new creature is added to the database. */
		@Override
		public void onChildAdded(
				@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
			try {
				// Get the creature that was added.
				Creature added = dataSnapshot.getValue(Creature.class);
				if (added != null) {
					String key = dataSnapshot.getKey();
					added.setKey(key);
					creatureList.add(added);
					if (index == -1) {
						index = 0;
						showCreature();
					}
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, "1: " + ex.getMessage());
			}
		}

		/** Handles the event that firebase
		 * generates when a creature is updated. */
		@Override
		public void onChildChanged(
				@NonNull DataSnapshot dataSnapshot, String s) {
			try {
				String key = dataSnapshot.getKey();
				Creature changed = dataSnapshot.getValue(Creature.class);
				if (changed != null) {
					changed.setKey(key);
					int i = creatureList.indexOf(changed);
					creatureList.set(i, changed);
					if (i == index) {
						showCreature();
					}
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, "2: " + ex.getMessage());
			}
		}

		/** Handles the event that firebase
		 * generates when a creature is deleted. */
		@Override
		public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
			try {
				String key = dataSnapshot.getKey();
				Creature removed = new Creature(key);
				int i = creatureList.indexOf(removed);
				if (i != -1) {
					creatureList.remove(i);
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
		public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
		}

		@Override
		public void onCancelled(DatabaseError error) {
			// Failed to read value
			Log.e(MainActivity.TAG, "4: DB error: " + error.toString());
		}
	}


	private void showCreature() {
		int size = creatureList.size();
		if (index >= size) {
			index = size - 1;
		}
		if (0 <= index && index < creatureList.size()) {
			Creature creature = creatureList.get(index);
			txtCreatureKey.setText(creature.getKey());
			txtName.setText(creature.getName());
			txtType.setText(creature.getType());
		}
		else {
			txtCreatureKey.setText("");
			txtName.setText("");
			txtType.setText("");
		}
	}
}
