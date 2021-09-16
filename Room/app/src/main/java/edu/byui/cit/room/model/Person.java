package edu.byui.cit.room.model;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Person {
	@PrimaryKey(autoGenerate = true)
	private long personKey;

	private String firstName;
	private String lastName;

	public Person() {
	}

	@Ignore
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getPersonKey() {
		return personKey;
	}

	void setPersonKey(long personKey) {
		this.personKey = personKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = (this == obj);
		if (!eq && obj != null && getClass() == obj.getClass()) {
			Person other = (Person)obj;
			eq = this.personKey == other.personKey &&
					this.firstName.equals(other.firstName) &&
					this.lastName.equals(other.lastName);
		}
		return eq;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personKey, firstName, lastName);
	}

	@Override
	@NonNull
	public String toString() {
		return personKey + " " + firstName + " " + lastName;
	}
}
