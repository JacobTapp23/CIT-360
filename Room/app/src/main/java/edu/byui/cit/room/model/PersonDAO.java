package edu.byui.cit.room.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public abstract class PersonDAO {
	@Query("SELECT COUNT(*) FROM Person")
	public abstract long count();

	@Query("SELECT * FROM Person")
	public abstract List<Person> getAllPeople();

	@Query("SELECT * FROM Person WHERE personKey = :key")
	public abstract Person getPersonByKey(long key);

	@Insert
	public void insert(Person person) {
		long id = realInsert(person);
		person.setPersonKey(id);
	}

	@Insert
	abstract long realInsert(Person person);

	@Update
	public abstract void update(Person person);

	@Delete
	public abstract void delete(Person person);

	@Query("DELETE FROM Person")
	public abstract void deleteAll();
}
