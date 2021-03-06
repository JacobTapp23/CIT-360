package edu.byui.cit.room.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


@Dao
public abstract class FacultyDAO extends PersonDAO {
	@Query("SELECT COUNT(*) FROM Faculty")
	public abstract long count();

	@Query("SELECT * FROM Person AS p JOIN Faculty AS f ON p.personKey = f.personKey")
	public abstract List<Faculty> getAllFaculty();

	@Query("SELECT * FROM Person AS p JOIN Faculty AS f ON p.personKey = f.personKey WHERE f.personKey = :key")
	public abstract Faculty getFacultyByKey(long key);

	@Transaction
	@Insert
	public void insert(Faculty faculty) {
		insert((Person)faculty);
		realInsert(new Faculty.Data(faculty));
	}

	@Insert
	abstract void realInsert(Faculty.Data data);

	@Transaction
	@Update
	public void update(Faculty faculty) {
		update((Person)faculty);
		realUpdate(new Faculty.Data(faculty));
	}

	@Update
	abstract void realUpdate(Faculty.Data data);

	// Delete is inherited from PersonDAO and
	// doesn't need to be overridden here.

	@Query("DELETE FROM Person WHERE personKey IN (SELECT personKey FROM " +
			"Faculty)")
	public abstract void deleteAll();
}
