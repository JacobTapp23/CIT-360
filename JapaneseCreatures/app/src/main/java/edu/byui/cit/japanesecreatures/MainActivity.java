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

			// Change the home icon to the menu
			// (hamburger) icon and make it visible.
			ActionBar actBar = getSupportActionBar();
			if (actBar != null) {
				actBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
				actBar.setDisplayHomeAsUpEnabled(true);
			}

			if (savedInstState == null) {
				// Create the main fragment and place it
				// as the first fragment in this activity.
				Fragment frag = new MainFrag();
				FragmentTransaction trans =
						getSupportFragmentManager().beginTransaction();
				trans.add(R.id.fragContainer, frag);
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
		// Inflate the action bar items.
		getMenuInflater().inflate(R.menu.action, menu);
		return true;
	}


	/** Handles a click on one of the items in the action bar. */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		boolean handled = true;
		try {
			switch (item.getItemId()) {
				case android.R.id.home:
					drawerLayout.openDrawer(GravityCompat.START);
					break;
				case R.id.actRoom:
					switchToRoomFrag();
					break;
				case R.id.actFirebase:
					switchToFirebaseFrag();
					break;
				case R.id.actBoth:
					switchToBothFrag();
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


	/** Handles a click on one of the items in the drawer. */
	private final class HandleNavClick
			implements NavigationView.OnNavigationItemSelectedListener {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
			boolean handled = true;
			try {
				switch (menuItem.getItemId()) {
					case R.id.navRoom:
						switchToRoomFrag();
						break;
					case R.id.navFirebase:
						switchToFirebaseFrag();
						break;
					case R.id.navBoth:
						switchToBothFrag();
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
}
