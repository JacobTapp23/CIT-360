package edu.byui.cit.creatures.controller;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.FirebaseApp;

import edu.byui.cit.creatures.R;


public class MainActivity extends AppCompatActivity {
	public static final String TAG = "Creatures";

    @Override
    protected void onCreate(Bundle savedInstState) {
    	try {
			super.onCreate(savedInstState);
			setContentView(R.layout.activity_main);
//			FirebaseApp.initializeApp(this);

			if (savedInstState == null) {
				// Create the main fragment and place it
				// as the first fragment in this activity.
				Fragment fragMain = new MainFrag();
				FragmentTransaction trans =
						getSupportFragmentManager().beginTransaction();
				trans.add(R.id.fragContainer, fragMain);
				trans.commit();
			}
		}
    	catch (Exception ex) {
    		Log.e(TAG, ex.getMessage(),  ex);
		}
	}
}