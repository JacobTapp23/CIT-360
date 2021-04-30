package edu.byui.cit.worktime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

import edu.byui.cit.worktime.model.AppDatabase;
import edu.byui.cit.worktime.model.Project;
import edu.byui.cit.worktime.model.ProjectDAO;
import edu.byui.cit.worktime.model.Session;
import edu.byui.cit.worktime.model.SessionDao;


/**
 * A simple activity that contains a single TextView and enables plain old
 * Java applications that write to the console to work in an Android app.
 */
public class MainActivity extends Activity {
	public static final String TAG = "Console";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			// Find the TextView and change System.out
			// so that it will print to the TextView.
			TextView console = findViewById(R.id.console);
			System.setOut(new PrintStream(new TextViewWriter(console)));
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
	}

	private static final class TextViewWriter extends OutputStream {
		private final StringBuilder buffer;
		private final TextView console;

		TextViewWriter(TextView console) {
			this.buffer = new StringBuilder();
			this.console = console;
		}

		// Write a single byte to the console TextView.
		@Override
		public void write(int b) {
			buffer.append(b);
			console.setText(buffer);
		}

		// Write an array of bytes to the console TextView.
		@Override
		public void write(byte[] b, int offs, int len) {
			buffer.append(new String(b, offs, len));
			console.setText(buffer);
		}
	}


	@Override
	protected void onStart() {
		try {
			super.onStart();
			main();
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
	}

	private void main() {
		/* Write your code in this function as if this function were
		 * public static void main(String[] args)
		 */
		Project proj1 = new Project( "Take shoes to doctor", "Tongue is weird color");
		Project proj2 = new Project( "Defrost the television", "picture is frozen");
		System.out.println(proj1.toString());
		System.out.println(proj2.toString());
		//Get the application context so that we can use it to connect to the database.
		Context appCtx = getApplicationContext();
		// Connect to the database
		AppDatabase db = AppDatabase.getInstance(appCtx);

		//Get the project data access object ProjectDAO
		ProjectDAO pdao = db.getProjectDAO();


		pdao.deleteAll();

		pdao.insert(proj1);
		pdao.insert(proj2);

		System.out.println(proj1.toString());
		System.out.println(proj2.toString());



	 	List<Project> allProjects = pdao.getAll();
	 	System.out.print(allProjects);

		 Date start1 = new Date(2021, 4, 23, 10,30);
		 Date end1 = new Date(2021, 4, 23, 12, 30);
		 Session s1 = new Session( proj1.getProjectKey(), "Check for weird odor",
		 start1, end1);
		 Session s2 = new Session( proj2.getProjectKey(), "Use remote control to turn up volume",
		 		start1, end1);
		 Session s3 = new Session( proj1.getProjectKey(), "Try to determine the 'sole' symptom",
		 		start1, end1);
		 System.out.println(proj1);
		 System.out.println(proj2);
		 System.out.println(s1);
		 System.out.println(s2);
		 System.out.println(s3);

		SessionDao sdao = db.getSessionDAO();
		sdao.deleteAll();

		sdao.insert(s1);
		sdao.insert(s2);
		sdao.insert(s3);

	}


}
