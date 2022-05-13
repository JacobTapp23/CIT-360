package edu.byui.cit.room.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


@Dao
public abstract class EmployeeDAO extends PersonDAO {
	@Query("SELECT COUNT(*) FROM Employee")
	public abstract long count();

	@Query("SELECT * FROM Person AS p JOIN Employee AS s ON p.personKey = s.personKey")
	public abstract List<Employee> getAllEmployees();

	@Query("SELECT * FROM Person AS p JOIN Employee AS s ON p.personKey = s.personKey WHERE s.personKey = :key")
	public abstract Employee getEmployeeByKey(long key);

	@Transaction
	@Insert
	public void insert(Employee employee) {
		insert(employee.personData);
		employee.setPersonKey(employee.personData.getPersonKey());
		realInsert(employee);
	}

	@Insert
	abstract void realInsert(Employee employee);

	@Transaction
	@Update
	public void update(Employee employee) {
		update(employee.personData);
		realUpdate(employee);
	}

	@Update
	abstract void realUpdate(Employee employee);

	@Delete
	public void delete(Employee employee) {
		delete(employee.personData);
	}

	@Query("DELETE FROM Person WHERE personKey IN (SELECT personKey FROM Employee)")
	public abstract void deleteAll();
}
