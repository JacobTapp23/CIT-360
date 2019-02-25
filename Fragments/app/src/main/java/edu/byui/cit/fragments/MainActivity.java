package edu.byui.cit.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
	public static final String TAG = "Fragments";

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private MainFrag fragMain;

	private String phone;


	@Override
	protected void onCreate(Bundle savedInstState) {
		try {
			super.onCreate(savedInstState);
			setContentView(R.layout.activity_main);

			// Set the toolbar that is in activity_main.xml
			// as the action bar for this app.
			Toolbar toolbar = findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);

			// Change the home icon on the action bar to
			// the menu (hamburger) icon and make it visible.
			ActionBar actBar = getSupportActionBar();
			actBar.setDisplayHomeAsUpEnabled(true);

			// Set up the drawer and its navigation items.
			drawerLayout = findViewById(R.id.drawerLayout);
			NavigationView nav = findViewById(R.id.navView);
			nav.setNavigationItemSelectedListener(new HandleNavClick());

			drawerToggle = new ActionBarDrawerToggle(
					this, drawerLayout,
					R.string.open, R.string.close);
			drawerToggle.syncState();

			if (savedInstState == null) {
				// Create the main fragment and place it
				// as the first fragment in this activity.
				FragmentTransaction trans =
						getSupportFragmentManager().beginTransaction();
				trans.add(R.id.fragContainer, getMainFrag());
				trans.commit();
			}
			else {
				// If this activity was previously running and was destroyed
				// and is now being recreated, then get the phone number from
				// the savedInstState.
				phone = savedInstState.getString("phone");
			}
		}
		catch (Exception ex) {
			Log.e(TAG, ex.toString());
		}
	}


	void setDrawerIndicatorEnabled(boolean enable) {
		drawerToggle.setDrawerIndicatorEnabled(enable);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			super.onCreateOptionsMenu(menu);
			// Inflate the action bar items.
			getMenuInflater().inflate(R.menu.action, menu);
		}
		catch (Exception ex) {
			Log.e(TAG, ex.toString());
		}
		return true;
	}


	/** Handles a click on one of the items in the action bar. */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean handled = false;
		try {
			// If the menu icon is displayed in the toolbar, the drawToggle
			// will handle the menu being pressed by opening the drawer.
			handled = drawerToggle.onOptionsItemSelected(item);

			if (! handled) {
				switch (item.getItemId()) {
					case android.R.id.home:
						// Respond to the user pressing the "back/up" button
						// on the action bar in the same way as if the user
						// pressed the left-facing triangle icon on the main
						// android toolbar.
						onBackPressed();
						break;
					case R.id.actMain:
						switchToFragment(getMainFrag());
						handled = true;
						break;
					case R.id.actSecond:
						switchToFragment(new SecondFrag());
						handled = true;
						break;
					case R.id.actThird:
						switchToFragment(new ThirdFrag());
						handled = true;
						break;
				}

				if (! handled) {
					handled = super.onOptionsItemSelected(item);
				}
			}
		}
		catch (Exception ex) {
			Log.e(TAG, ex.toString());
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
						switchToFragment(getMainFrag());
						handled = true;
						break;
					case R.id.navSecond:
						switchToFragment(new SecondFrag());
						handled = true;
						break;
					case R.id.navThird:
						switchToFragment(new ThirdFrag());
						handled = true;
						break;
				}
				drawerLayout.closeDrawers();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.toString());
			}
			return handled;
		}
	}


	MainFrag getMainFrag() {
		if (fragMain == null) {
			fragMain = new MainFrag();
		}
		return fragMain;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	/** Displays a different fragment in the fragment container. */
	void switchToFragment(Fragment toShow) {
		// Replace whatever is in the fragContainer view with
		// toShow, and add the transaction to the back stack so
		// that the user can navigate back.
		FragmentTransaction trans =
				getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.fragContainer, toShow);
		trans.addToBackStack(null);
		trans.commit();
	}


	@Override
	public void onSaveInstanceState(Bundle savedInstState) {
		// This method is called when the activity is being destroyed and
		// then will be recreated. For example, if the user rotates the
		// to its side, the activity will be destroyed and recreated.

		// Save the phone number that came from the second fragment, so
		// that it will be restored in onCreate when this activity is
		// recreated.
		savedInstState.putString("phone", phone);
		super.onSaveInstanceState(savedInstState);
	}
}
