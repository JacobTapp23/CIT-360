package edu.byui.cit.room.model;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "Employee",
		foreignKeys = @ForeignKey(
				entity = Person.class,
				parentColumns = "personKey",
				childColumns = "personKey",
				onDelete = CASCADE))
public class Employee {
	@Embedded
	@Ignore
	Person personData;
	// The Employee class was another attempt at implementing
	// inheritance and using Room to store the data. However, the
	// Employee class doesn't work. When reading an Employee object
	// from the DB, Room will not store personKey, firstName, and
	// lastName because of the @Ignore annotation.

	@PrimaryKey
	private long personKey;
	private String title;

	Employee() {
		personData = new Person();
	}

	@Ignore
	public Employee(String firstName, String lastName, String title) {
		personData = new Person(firstName, lastName);
		this.title = title;
	}

	public long getPersonKey() {
		personKey = personData.getPersonKey();
		return personKey;
	}

	void setPersonKey(long personKey) {
		personData.setPersonKey(personKey);
		this.personKey = personKey;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public boolean equals(Object obj) {
		boolean eq = (this == obj);
		if (!eq && obj != null && getClass() == obj.getClass()) {
			Employee other = (Employee)obj;
			eq = personData.equals(other.personData) &&
					this.title.equals(other.title);
		}
		return eq;
	}

	@Override
	public int hashCode() {
		int pCode = super.hashCode();
		int sCode = Objects.hashCode(title);
		return pCode + sCode;
	}

	@Override
	@NonNull
	public String toString() {
		return personData.toString() + " " + title;
	}
}
