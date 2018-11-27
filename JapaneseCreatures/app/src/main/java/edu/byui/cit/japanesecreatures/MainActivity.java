package edu.byui.cit.japanesecreatures;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import edu.byui.cit.japanesecreatures.model.AppDatabase;
import edu.byui.cit.japanesecreatures.model.Creature;
import edu.byui.cit.japanesecreatures.model.CreatureDAO;


public final class MainActivity extends Activity {
	private static final String TAG = "Creatures";
	private EditText txtCreatureID, txtName, txtType;
	private List<Creature> allCreatures;
	private int index = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtCreatureID = findViewById(R.id.txtCreatureID);
		txtName = findViewById(R.id.txtName);
		txtType = findViewById(R.id.txtType);

		Button btnPrev = findViewById(R.id.btnPrev);
		Button btnNext = findViewById(R.id.btnNext);
		Button btnInsert = findViewById(R.id.btnInsert);
		Button btnUpdate = findViewById(R.id.btnUpdate);
		Button btnDelete = findViewById(R.id.btnDelete);
		Button btnDeleteAll = findViewById(R.id.btnDeleteAll);
		btnPrev.setOnClickListener(new HandlePrev());
		btnNext.setOnClickListener(new HandleNext());
		btnInsert.setOnClickListener(new HandleInsert());
		btnUpdate.setOnClickListener(new HandleUpdate());
		btnDelete.setOnClickListener(new HandleDelete());
		btnDeleteAll.setOnClickListener(new HandleDeleteAll());

		Context appCtx = getApplicationContext();
		AppDatabase db = AppDatabase.getInstance(appCtx);
		CreatureDAO dao = db.getCreatureDAO();

		Creature[] testCreatures = {
				new Creature("charizard", "fire"),
				new Creature("squirtle", "water")
		};
		for (Creature creature : testCreatures) {
			dao.insert(creature);
		}

		allCreatures = dao.getAll();
		if (allCreatures.size() > 0) {
			index = 0;
			showCreature();
		}
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
				Log.e(TAG, ex.getMessage());
			}
		}
	}


	private final class HandleNext implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				int last = allCreatures.size() - 1;
				if (++index > last) {
					index = last;
				}
				showCreature();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
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
				Context appCtx = getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.insert(creature);

				allCreatures = dao.getAll();
				index = allCreatures.size() - 1;
				showCreature();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
			}
		}
	}


	private final class HandleUpdate implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				String text = txtCreatureID.getText().toString().trim();
				long creatureID = Long.parseLong(text);
				String name = txtName.getText().toString().trim();
				String type = txtType.getText().toString().trim();
				Creature creature = new Creature(creatureID, name, type);
				Context appCtx = getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.update(creature);

				allCreatures = dao.getAll();
				showCreature();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
			}
		}
	}


	private final class HandleDelete implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				String text = txtCreatureID.getText().toString().trim();
				long creatureID = Long.parseLong(text);
				Creature creature = new Creature(creatureID);
				Context appCtx = getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.delete(creature);

				allCreatures = dao.getAll();
				int last = allCreatures.size() - 1;
				if (index > last) {
					index = last;
				}
				showCreature();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
			}
		}
	}


	private final class HandleDeleteAll implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				Context appCtx = getApplicationContext();
				AppDatabase db = AppDatabase.getInstance(appCtx);
				CreatureDAO dao = db.getCreatureDAO();
				dao.deleteAll();
				showCreature();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
			}
		}
	}


	private void showCreature() {
		if (0 <= index && index < allCreatures.size()) {
			Creature creature = allCreatures.get(index);
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
