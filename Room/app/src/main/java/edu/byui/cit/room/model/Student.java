package edu.byui.cit.room.model;

import java.util.Objects;

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


	@Override
	public boolean equals(Object obj) {
		boolean eq = (this == obj);
		if (!eq && obj != null && getClass() == obj.getClass()) {
			Student other = (Student)obj;
			eq = super.equals(other) &&
					floatEquals(this.getGpa(), other.getGpa(), 2);
		}
		return eq;
	}

	public static boolean floatEquals(float e, float f, int maxULPs) {
		boolean eq;
		boolean enan = Float.isNaN(e);
		boolean fnan = Float.isNaN(f);
		if (enan && fnan) {
			eq = true;
		}
		else if (enan || fnan) {
			eq = false;
		}
		else {
			boolean einf = Float.isInfinite(e);
			boolean finf = Float.isInfinite(f);
			if (einf && finf) {
				int esig = (int)Math.signum(e);
				int fsig = (int)Math.signum(f);
				eq = (esig == fsig);
			}
			else if (einf || finf) {
				eq = false;
			}
			else if (e == 0 && f == 0) {
				eq = true;
			}
			else {
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
	public String toString() {
		return super.toString() + " " + getGpa();
	}
}
