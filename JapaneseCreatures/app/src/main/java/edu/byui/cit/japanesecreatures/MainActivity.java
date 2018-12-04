package edu.byui.cit.japanesecreatures;

import android.content.Context;
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


public final class MainActivity extends AppCompatActivity {
	static final String TAG = "Creatures";

	View.OnClickListener roomClickHandler, firebaseClickHandler, bothClickHandler;
	private Fragment fragRoom, fragFirebase, fragBoth;
	private DrawerLayout drawerLayout;


	@Override
	protected void onCreate(Bundle savedInstState) {
		super.onCreate(savedInstState);
		try {
			Context ctx = getApplicationContext();
			FirebaseApp.initializeApp(ctx);
			setContentView(R.layout.activity_main);

			roomClickHandler = new HandleRoomClick();
			firebaseClickHandler = new HandleFirebaseClick();
			bothClickHandler = new HandleBothClick();

			Toolbar toolbar = findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);
			ActionBar actBar = getSupportActionBar();
			actBar.setDisplayHomeAsUpEnabled(true);

			// TODO: change the home icon to the menu (hamburger) icon.
//			actBar.setHomeAsUpIndicator(R.id.ic_menu);

			if (savedInstState == null) {
				// Create the main fragment and place it
				// as the first fragment in this activity.
				Fragment frag = new MainFrag();
				FragmentTransaction trans =
						getSupportFragmentManager().beginTransaction();
				trans.add(R.id.fragContainer, frag, MainFrag.TAG);
				trans.commit();
			}

			drawerLayout = findViewById(R.id.drawerLayout);
			NavigationView nav = findViewById(R.id.navView);
			nav.setNavigationItemSelectedListener(new HandleNavClick());
		}
		catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate our menu from the resources by using the menu inflater.
		getMenuInflater().inflate(R.menu.menu_action, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		boolean handled = true;
		try {
			switch (item.getItemId()) {
				case android.R.id.home:
					// If the MainFrag is visible then open the drawer,
					// otherwise respond as if the user pressed the back button.
					Fragment frag = getSupportFragmentManager().findFragmentByTag(MainFrag.TAG);
					if (frag != null && frag.isVisible()) {
						drawerLayout.openDrawer(GravityCompat.START);
					}
					else {
						onBackPressed();
					}
					break;
				case R.id.actRoom:
					switchToRoom();
					break;
				case R.id.actFirebase:
					switchToFirebase();
					break;
				case R.id.actBoth:
					switchToBoth();
					break;
				default:
					handled = false;
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


	private final class HandleNavClick
			implements NavigationView.OnNavigationItemSelectedListener {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
			boolean handled = true;
			try {
				switch (menuItem.getItemId()) {
					case R.id.navRoom:
						switchToRoom();
						break;
					case R.id.navFirebase:
						switchToFirebase();
						break;
					case R.id.navBoth:
						switchToBoth();
						break;
					default:
						handled = false;
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
			switchToRoom();
		}
	}

	/** Handles a click on the Firebase button. */
	private final class HandleFirebaseClick implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			switchToFirebase();
		}
	}

	/** Handles a click on the Firebase button. */
	private final class HandleBothClick implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			switchToBoth();
		}
	}


	private void switchToRoom() {
		if (fragRoom == null) {
			fragRoom = new RoomFrag();
		}
		switchFragment(fragRoom, RoomFrag.TAG);
	}

	private void switchToFirebase() {
		if (fragFirebase == null) {
			fragFirebase = new FirebaseFrag();
		}
		switchFragment(fragFirebase, FirebaseFrag.TAG);
	}

	private void switchToBoth() {
		if (fragBoth == null) {
			fragBoth = new BothFrag();
		}
		switchFragment(fragBoth, BothFrag.TAG);
	}

	/** Displays a different fragment in the frag container. */
	private void switchFragment(Fragment fragment, String tag) {
		// Replace whatever is in the fragContainer view with
		// fragment, and add the transaction to the back stack so
		// that the user can navigate back.
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.fragContainer, fragment, tag);
		trans.addToBackStack(null);
		trans.commit();
	}
}
