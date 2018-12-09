package edu.byui.cit.japanesecreatures;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.FirebaseApp;


/** This is an example app that shows how to use the following:
 * 1. Action bar
 * 2. Drawer
 * 3. Fragments
 * 4. User preferences
 * 5. Room database
 * 6. Firebase realtime database
 */
public final class MainActivity extends AppCompatActivity {
	static final String TAG = "Creatures";
	private static final String USERNAME_KEY = "username";

	View.OnClickListener roomClickHandler, firebaseClickHandler, bothClickHandler;
	private Fragment fragMain, fragRoom, fragFirebase, fragBoth;
	private DrawerLayout drawerLayout;

	private String username;


	@Override
	protected void onCreate(Bundle savedInstState) {
		try {
			super.onCreate(savedInstState);
			Context ctx = getApplicationContext();
			FirebaseApp.initializeApp(ctx);
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

			// Create three click handlers that are used by the items on the
			// action bar, the items in the drawer, and the buttons in MainFrag.
			roomClickHandler = new HandleRoomClick();
			firebaseClickHandler = new HandleFirebaseClick();
			bothClickHandler = new HandleBothClick();
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
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
				case R.id.actRoom:
					switchToRoomFrag();
					handled = true;
					break;
				case R.id.actFirebase:
					switchToFirebaseFrag();
					handled = true;
					break;
				case R.id.actBoth:
					switchToBothFrag();
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
					case R.id.navRoom:
						switchToRoomFrag();
						handled = true;
						break;
					case R.id.navFirebase:
						switchToFirebaseFrag();
						handled = true;
						break;
					case R.id.navBoth:
						switchToBothFrag();
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


	/** Handles a click on the Room button. */
	private final class HandleRoomClick implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			switchToRoomFrag();
		}
	}

	/** Handles a click on the Firebase button. */
	private final class HandleFirebaseClick implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			switchToFirebaseFrag();
		}
	}

	/** Handles a click on the Both button. */
	private final class HandleBothClick implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			switchToBothFrag();
		}
	}


	private void switchToRoomFrag() {
		if (fragRoom == null) {
			fragRoom = new RoomFrag();
		}
		switchFragment(fragRoom);
	}

	private void switchToFirebaseFrag() {
		if (fragFirebase == null) {
			fragFirebase = new FirebaseFrag();
		}
		switchFragment(fragFirebase);
	}

	private void switchToBothFrag() {
		if (fragBoth == null) {
			fragBoth = new BothFrag();
		}
		switchFragment(fragBoth);
	}

	/** Displays a different fragment in the frag container. */
	private void switchFragment(Fragment fragment) {
		// Replace whatever is in the fragContainer view with
		// fragment, and add the transaction to the back stack so
		// that the user can navigate back.
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.fragContainer, fragment);
		trans.addToBackStack(null);
		trans.commit();
	}


	public String getUsername() {
		if (username == null) {
			SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
			username = prefs.getString(USERNAME_KEY, "");
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(USERNAME_KEY, username);
		editor.apply();
	}
}
