package edu.byui.cit.room.controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import edu.byui.cit.room.R;
import edu.byui.cit.room.model.AppDatabase;
import edu.byui.cit.room.model.Faculty;
import edu.byui.cit.room.model.FacultyDAO;
import edu.byui.cit.room.model.Person;
import edu.byui.cit.room.model.PersonDAO;
import edu.byui.cit.room.model.Student;
import edu.byui.cit.room.model.StudentDAO;


public final class MainFrag extends Fragment {
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		try {
			super.onCreateView(inflater, container, savedInstanceState);
			view = inflater.inflate(R.layout.frag_main, container, false);


			// Connect to the Room database and get the
			// data access object for the Creature table.
			MainActivity act = getMainActivity();
			Context appCtx = act.getApplicationContext();
			AppDatabase db = AppDatabase.getInstance(appCtx);
			PersonDAO pdao = db.getPersonDAO();
			StudentDAO sdao = db.getStudentDAO();
			FacultyDAO fdao = db.getFacultyDAO();
			pdao.deleteAll();

			// Add two test people to the Person table.
			Person[] testPersons = {
					new Person("Samantha", "Sanders"),
					new Person("Lucy", "Layton")
			};
			for (Person person : testPersons) {
				pdao.insert(person);
			}

			Student[] testStudents = {
					new Student("Jose", "Jimenez", 3.87F),
					new Student("Harold", "Hathaway", 3.75F),
			};
			for (Student student : testStudents) {
				sdao.insert(student);
			}

			Faculty[] testFaculty = {
					new Faculty("Alice", "Anderson", "STC 310N"),
			};
			for (Faculty faculty : testFaculty) {
				fdao.insert(faculty);
			}

			// Display the username in the username text field.
			EditText txtUsername = view.findViewById(R.id.txtUsername);
			txtUsername.setText(act.getUsername());
			txtUsername.setOnFocusChangeListener(new HandleUsername());

			// Add an action listener to each button.
			Button button = view.findViewById(R.id.btnRoom);
			button.setOnClickListener(new HandleRoomClick());
		}
		catch (Exception ex) {
			Log.e(MainActivity.TAG, ex.toString());
		}
		return view;
	}


	private final class HandleUsername implements View.OnFocusChangeListener {
		@Override
		public void onFocusChange(View editText, boolean hasFocus) {
			try {
				if (!hasFocus) {
					EditText txtUsername = (EditText)editText;
					String username = txtUsername.getText().toString().trim();
					MainActivity act = getMainActivity();
					act.setUsername(username);
				}
			}
			catch (Exception ex) {
				Log.e(MainActivity.TAG, ex.toString());
			}
		}
	}


	private final class HandleRoomClick implements View.OnClickListener {
		@Override
		public void onClick(View button) {
			MainActivity activity = getMainActivity();
			Fragment fragRoom = new RoomFrag();
			activity.switchFragment(fragRoom);
		}
	}
}
