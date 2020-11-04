package edu.byui.cit.creatures.controller;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import edu.byui.cit.creatures.roomModel.AppDatabase;
import edu.byui.cit.creatures.roomModel.Creature;
import edu.byui.cit.creatures.roomModel.CreatureDAO;

import static org.junit.Assert.assertEquals;


public final class TestRoomDatabase {
	// Get the database and the data access objects.
	private Context ctx = InstrumentationRegistry.getInstrumentation().getTargetContext();
	private AppDatabase db = AppDatabase.getInstance(ctx);
	private CreatureDAO dao = db.getCreatureDAO();


	@Test
	public void useAppContext() {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		assertEquals("edu.byui.cit.japanesecreatures", appContext.getPackageName());
	}


	@Test
	public void testCreatureTable() {
		// Delete all creatures and verify that
		// there are no rows in the Creature table.
		dao.deleteAll();
		assertEquals(0, dao.count());

		// Insert a Creature object into the Creature
		// table and verify that it was correctly inserted.
		Creature first = new Creature("Mowgli", "human");
		dao.insert(first);
		assertEquals(1, dao.count());
		Creature saved = dao.getByKey(first.getKey());
		assertEquals(first, saved);
	}
}
