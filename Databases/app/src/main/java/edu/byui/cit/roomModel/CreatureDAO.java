package edu.byui.cit.roomModel;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public abstract class CreatureDAO {
	@Query("SELECT COUNT(*) FROM Creature")
	public abstract long count();

	@Query("SELECT * FROM Creature")
	public abstract List<Creature> getAll();

	@Insert
	public abstract void insert(Creature creature);

	@Update
	public abstract void update(Creature creature);

	@Delete
	public abstract void delete(Creature creature);

	@Query("DELETE FROM Creature")
	public abstract void deleteAll();
}
