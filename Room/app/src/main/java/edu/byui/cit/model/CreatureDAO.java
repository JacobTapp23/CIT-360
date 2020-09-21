package edu.byui.cit.model;

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

	public long insert(Creature creature) {
		long id = insertH(creature);
		creature.setKey(id);
		return id;
	}

	@Insert
	abstract long insertH(Creature creature);

	@Update
	public abstract void update(Creature creature);

	@Delete
	public abstract void delete(Creature creature);

	@Query("DELETE FROM Creature")
	public abstract void deleteAll();
}
