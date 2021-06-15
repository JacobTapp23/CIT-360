package edu.byui.cit.room.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "Student",
		foreignKeys = @ForeignKey(
				entity = Person.class,
				parentColumns = "personKey",
				childColumns = "personKey",
				onDelete = CASCADE))
public class StudentData {
	@PrimaryKey
	private long personKey;

	private float gpa;

	StudentData(float gpa) {
		this.gpa = gpa;
	}

	long getPersonKey() {
		return personKey;
	}

	void setPersonKey(long personKey) {
		this.personKey = personKey;
	}

	float getGpa() {
		return gpa;
	}

	void setGpa(float gpa) {
		this.gpa = gpa;
	}
}
