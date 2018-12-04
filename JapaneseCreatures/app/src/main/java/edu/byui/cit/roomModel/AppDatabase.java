package edu.byui.cit.roomModel;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = { Creature.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase singleton;

    public static AppDatabase getInstance(Context appCtx) {
        if (singleton == null) {
            singleton = Room.databaseBuilder(
                    appCtx, AppDatabase.class, "Record").allowMainThreadQueries().build();
        }
        return singleton;
    }

    public abstract CreatureDAO getCreatureDAO();
}
