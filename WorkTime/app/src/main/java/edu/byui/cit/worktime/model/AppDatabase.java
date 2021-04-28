package edu.byui.cit.worktime.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = { Creature.class }, version = 2, exportSchema = false)
@TypeConverters({ Converters.class })
public abstract class AppDatabase extends RoomDatabase {
	private static edu.byui.cit.room.model.AppDatabase singleton = null;

	public static edu.byui.cit.room.model.AppDatabase getInstance(Context appCtx) {
		if (singleton == null) {
			singleton = Room.databaseBuilder(
					appCtx, edu.byui.cit.room.model.AppDatabase.class, "Creatures")
					.allowMainThreadQueries()
					.fallbackToDestructiveMigration()
					.build();
		}
		return singleton;
	}

	public abstract CreatureDAO getCreatureDAO();
}
