package edu.byui.cit.room.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


@Dao
public abstract class StudentDAO extends PersonDAO {
	@Query("SELECT COUNT(*) FROM Student")
	public abstract long count();

	@Transaction
	@Query("SELECT * FROM Person AS p JOIN Student AS s ON p.personKey = s.personKey")
	public abstract List<Student> getAllStudents();

	@Transaction
	@Query("SELECT * FROM Person AS p JOIN Student AS s ON p.personKey = s.personKey WHERE s.personKey = :key")
	public abstract Student getStudentByKey(long key);

	@Transaction
	@Insert
	public void insert(Student student) {
		insert(student.personData);
		student.studentData.setPersonKey(student.getPersonKey());
		insert(student.studentData);
	}

	@Insert
	abstract void insert(StudentData studentData);

	@Transaction
	@Update
	public void update(Student student) {
		update(student.personData);
		realUpdate(student.studentData);
	}

	@Update
	abstract void realUpdate(StudentData studentData);

	@Delete
	public void delete(Student student) {
		delete(student.personData);
	}

	@Query("DELETE FROM Person WHERE personKey IN (SELECT personKey FROM Student)")
	public abstract void deleteAll();
}
