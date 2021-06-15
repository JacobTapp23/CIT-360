package edu.byui.cit.room.model;

import java.util.Objects;


public class Faculty extends Person {
	private String office;

	public Faculty(String firstName, String lastName, String office) {
		super(firstName, lastName);
		this.office = office;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = (this == obj);
		if (!eq && obj != null && getClass() == obj.getClass()) {
			Faculty other = (Faculty)obj;
			eq = super.equals(other) &&
					this.office.equals(other.office);
		}
		return eq;
	}

	@Override
	public int hashCode() {
		int pCode = super.hashCode();
		int sCode = Objects.hashCode(office);
		return pCode + sCode;
	}

	@Override
	public String toString() {
		return super.toString() + " " + office;
	}
}
