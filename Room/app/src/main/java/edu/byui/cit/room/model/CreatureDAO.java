package edu.byui.cit.room.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public abstract class CreatureDAO {
	@Query("SELECT COUNT(*) FROM Creature")
	public abstract long count();

	@Query("SELECT * FROM Creature")
	public abstract List<Creature> getAll();

	@Query("SELECT * FROM Creature WHERE `key` = :key")
	public abstract Creature getByKey(long key);

	public void insert(Creature creature) {
		long id = realInsert(creature);
		creature.setKey(id);
	}

	@Insert
	abstract long realInsert(Creature creature);

	@Update
	public abstract void update(Creature creature);

	@Delete
	public abstract void delete(Creature creature);

	@Query("DELETE FROM Creature")
	public abstract void deleteAll();
}
