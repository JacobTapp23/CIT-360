package edu.byui.cit.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
	public static final String TAG = "Fragments";

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private FirstFrag fragFirst;

	private String phone;
	private String response;


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
			assert actBar != null;
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
				fragFirst = new FirstFrag();
				FragmentTransaction trans =
						getSupportFragmentManager().beginTransaction();
				trans.add(R.id.fragContainer, fragFirst);
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
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		boolean handled = false;
		try {
			// If the menu icon is displayed in the toolbar, the drawToggle
			// will handle the menu being pressed by opening the drawer.
			handled = drawerToggle.onOptionsItemSelected(item);

			if (! handled) {
				int itemId = item.getItemId();
				if (itemId == android.R.id.home) {
					// Respond to the user pressing the "back/up" button
					// on the action bar in the same way as if the user
					// pressed the left-facing triangle icon on the main
					// android toolbar.
					onBackPressed();
				}
				else if (itemId == R.id.actFirst) {
					switchToFragment(fragFirst, "main");
					handled = true;
				}
				else if (itemId == R.id.actSecond) {
					switchToFragment(new SecondFrag(), "second");
					handled = true;
				}
				else if (itemId == R.id.actThird) {
					switchToFragment(new ThirdFrag(), "third");
					handled = true;
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
				int itemId = menuItem.getItemId();
				if (itemId == R.id.navFirst) {
					switchToFragment(fragFirst, "main");
					handled = true;
				}
				else if (itemId == R.id.navSecond) {
					switchToFragment(new SecondFrag(), "second");
					handled = true;
				}
				else if (itemId == R.id.navThird) {
					switchToFragment(new ThirdFrag(), "third");
					handled = true;
				}
				drawerLayout.closeDrawers();
			}
			catch (Exception ex) {
				Log.e(TAG, ex.toString());
			}
			return handled;
		}
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}


	/** Displays a different fragment in the fragment container. */
	void switchToFragment(Fragment toShow, String tag) {
		// Replace whatever is in the fragContainer view with
		// toShow, and add the transaction to the back stack so
		// that the user can navigate back.
		FragmentTransaction trans =
				getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.fragContainer, toShow, tag);
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
