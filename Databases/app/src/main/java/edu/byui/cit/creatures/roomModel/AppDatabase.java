package edu.byui.cit.creatures.roomModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;


@Database(entities = { Creature.class }, version = 2, exportSchema = false)
@TypeConverters({ Converters.class })
public abstract class AppDatabase extends RoomDatabase {
	private static AppDatabase singleton;

	public static AppDatabase getInstance(Context appCtx) {
		if (singleton == null) {
			singleton = Room.databaseBuilder(
					appCtx, AppDatabase.class, "Creatures")
					.allowMainThreadQueries()
					.fallbackToDestructiveMigration()
					.build();
		}
		return singleton;
	}

	public abstract CreatureDAO getCreatureDAO();
}
