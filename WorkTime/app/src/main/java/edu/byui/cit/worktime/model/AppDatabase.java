package edu.byui.cit.worktime.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = { Project.class, Session.class }, version = 1, exportSchema = false)
@TypeConverters({ Converters.class })
public abstract class AppDatabase extends RoomDatabase {
	private static AppDatabase singleton = null;

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

	public abstract ProjectDAO getProjectDAO();
	public abstract SessionDao getSessionDAO();
}
