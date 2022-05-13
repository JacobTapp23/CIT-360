package edu.byui.cit.image;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public final class MainActivity extends AppCompatActivity {
    public static final String TAG = "WorkTime";

    @Override
    protected void onCreate(Bundle savedInstState) {
        try {
            super.onCreate(savedInstState);
            setContentView(R.layout.activity_main);

            if (savedInstState == null) {
                // Create the main fragment and place it
                // as the first fragment in this activity.
                Fragment frag = new MainFrag();
                FragmentTransaction trans =
                        getSupportFragmentManager().beginTransaction();
                trans.add(R.id.fragContainer, frag);
                trans.commit();
            }

        }
        catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
    }
}
