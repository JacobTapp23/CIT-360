package edu.byui.cit.room.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = { Creature.class, Person.class, StudentData.class, FacultyData.class },
		version = 7, exportSchema = false)
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

	public abstract CreatureDAO getCreatureDAO();
	public abstract PersonDAO getPersonDAO();
	public abstract StudentDAO getStudentDAO();
	public abstract FacultyDAO getFacultyDAO();
}
