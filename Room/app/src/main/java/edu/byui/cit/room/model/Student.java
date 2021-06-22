package edu.byui.cit.room.model;

import java.util.Objects;

import androidx.annotation.NonNull;
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
		personData = new Person();
		studentData = new StudentData();
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


	@Override
	public boolean equals(Object obj) {
		boolean eq = (this == obj);
		if (!eq && obj != null && getClass() == obj.getClass()) {
			Student other = (Student)obj;
			eq = personData.equals(other.personData) &&
					floatEquals(this.getGpa(), other.getGpa(), 2);
		}
		return eq;
	}

	public static boolean floatEquals(float e, float f, int maxULPs) {
		boolean eq;
		boolean enan = Float.isNaN(e);
		boolean fnan = Float.isNaN(f);
		if (enan && fnan) {
			// Both e and f are not numbers (NaN),
			// so we consider them to be equal.
			eq = true;
		}
    else if (enan || fnan) {
			// Either e or f but not both is not a
			// number (NaN), so they aren't equal.
			eq = false;
		}
		else {
			boolean einf = Float.isInfinite(e);
			boolean finf = Float.isInfinite(f);
			if (einf && finf) {
				// Both e and f are infinite, so they
				// are equal if their signs are equal,
				// otherwise they are not equal.
				int esig = (int)Math.signum(e);
				int fsig = (int)Math.signum(f);
				eq = (esig == fsig);
			}
        else if (einf || finf) {
				// Either e or f but not both is
				// infinite, so they aren't equal.
				eq = false;
			}
			else {
				// Both e and f are finite numbers, so
				// compare them to see if they are
				// close enough to be considered equal.
				float ulp = Math.ulp(Math.max(Math.abs(e), Math.abs(f)));
				eq = (Math.abs(e - f) <= ulp * maxULPs);
			}
		}
		return eq;
	}

	@Override
	public int hashCode() {
		int pCode = super.hashCode();
		int sCode = Objects.hashCode(getGpa());
		return pCode + sCode;
	}

	@Override
	@NonNull
	public String toString() {
		return super.toString() + " " + getGpa();
	}
}
