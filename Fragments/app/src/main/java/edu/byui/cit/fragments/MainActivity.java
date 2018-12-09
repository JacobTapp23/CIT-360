package edu.byui.cit.fragments;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
	public static final String TAG = "Fragments";

	private DrawerLayout drawerLayout;
	private Fragment fragMain, fragSecond, fragThird;


	@Override
	protected void onCreate(Bundle savedInstState) {
		super.onCreate(savedInstState);
		setContentView(R.layout.activity_main);

		// Set the toolbar that is in main_activity.xml
		// as the action bar for this app.
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Change the home icon on the action bar to
		// the menu (hamburger) icon and make it visible.
		ActionBar actBar = getSupportActionBar();
		if (actBar != null) {
			actBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
			actBar.setDisplayHomeAsUpEnabled(true);
		}

		if (savedInstState == null) {
			// Create the main fragment and place it
			// as the first fragment in this activity.
			fragMain = new MainFrag();
			FragmentTransaction trans =
					getSupportFragmentManager().beginTransaction();
			trans.add(R.id.fragContainer, fragMain);
			trans.commit();
		}

		// Set up the drawer and its navigation items.
		drawerLayout = findViewById(R.id.drawerLayout);
		NavigationView nav = findViewById(R.id.navView);
		nav.setNavigationItemSelectedListener(new HandleNavClick());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			super.onCreateOptionsMenu(menu);
			// Inflate the action bar items.
			getMenuInflater().inflate(R.menu.action, menu);
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
		return true;
	}


	/** Handles a click on one of the items in the action bar. */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		boolean handled = false;
		try {
			switch (item.getItemId()) {
				case android.R.id.home:
					drawerLayout.openDrawer(GravityCompat.START);
					handled = true;
					break;
				case R.id.actMain:
					switchToMainFrag();
					handled = true;
					break;
				case R.id.actSecond:
					switchToSecondFrag();
					handled = true;
					break;
				case R.id.actThird:
					switchToThirdFrag();
					handled = true;
					break;
			}
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
		if (!handled) {
			handled = super.onOptionsItemSelected(item);
		}
		return handled;
	}


	/** Handles a click on one of the items in the drawer. */
	private final class HandleNavClick
			implements NavigationView.OnNavigationItemSelectedListener {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
			boolean handled = false;
			try {
				switch (menuItem.getItemId()) {
					case R.id.navMain:
						switchToMainFrag();
						handled = true;
						break;
					case R.id.navSecond:
						switchToSecondFrag();
						handled = true;
						break;
					case R.id.navThird:
						switchToThirdFrag();
						handled = true;
						break;
				}
				drawerLayout.closeDrawers();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
			}
			return handled;
		}
	}


	void switchToMainFrag() {
		switchFragment(fragMain);
	}

	void switchToSecondFrag() {
		if (fragSecond == null) {
			fragSecond = new SecondFrag();
		}
		switchFragment(fragSecond);
	}

	void switchToThirdFrag() {
		if (fragThird == null) {
			fragThird = new ThirdFrag();
		}
		switchFragment(fragThird);
	}

	/** Displays a different fragment in the frag container. */
	private void switchFragment(Fragment fragment) {
		// Replace whatever is in the fragContainer view with
		// fragment, and add the transaction to the back stack so
		// that the user can navigate back.
		FragmentTransaction trans = getSupportFragmentManager()
				.beginTransaction();
		trans.replace(R.id.fragContainer, fragment);
		trans.addToBackStack(null);
		trans.commit();
	}
}
