package edu.byui.cit.fab;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
	public static final String TAG = "FAB";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			Toolbar toolbar = findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);

			FloatingActionButton fab = findViewById(R.id.fab);
			fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Snackbar.make(view, "Replace with your own action",
							Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				}
			});
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean r = false;
		try {
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.menu_main, menu);
			r = true;
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
		return r;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		try {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();

			if (id == R.id.action_settings) {
				return true;
			}
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage(), ex);
		}
		return super.onOptionsItemSelected(item);
	}
}
