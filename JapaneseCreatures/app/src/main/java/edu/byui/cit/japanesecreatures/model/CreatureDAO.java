package edu.byui.cit.japanesecreatures.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface CreatureDAO {
    @Query("SELECT * FROM Creature")
    List<Creature> getAll();

    @Insert
    void insert(Creature creature);

	@Update
	void update(Creature creature);

    @Delete
    void delete(Creature creature);

    @Query("DELETE FROM Creature")
    void deleteAll();
}
