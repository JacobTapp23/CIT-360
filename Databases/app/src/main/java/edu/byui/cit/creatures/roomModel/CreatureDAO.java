package edu.byui.cit.creatures.roomModel;

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
