package edu.byui.cit.room.model;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;


public class Student {
	@Embedded
	Person personData;
	@Relation(
			parentColumn = "personKey",
			entityColumn = "personKey"
	)
	StudentData studentData;

	Student() {
	}

	Person getPersonData() {
		return personData;
	}

	void setPersonData(Person personData) {
		this.personData = personData;
	}

	StudentData getStudentData() {
		return studentData;
	}

	void setStudentData(StudentData studentData) {
		this.studentData = studentData;
	}


	@Ignore
	public Student(String firstName, String lastName, float gpa) {
		personData = new Person(firstName, lastName);
		studentData = new StudentData(gpa);
	}

	public long getPersonKey() {
		return personData.getPersonKey();
	}

	public String getFirstName() {
		return personData.getFirstName();
	}

	public void setFirstName(String firstName) {
		personData.setFirstName(firstName);
	}

	public String getLastName() {
		return personData.getLastName();
	}

	public void setLastName(String lastName) {
		personData.setLastName(lastName);
	}

	public float getGpa() {
		return studentData.getGpa();
	}

	public void setGpa(float gpa) {
		studentData.setGpa(gpa);
	}
}
