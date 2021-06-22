package edu.byui.cit.room.model;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


public class Faculty extends Person {

	@Entity(tableName = "Faculty",
			foreignKeys = @ForeignKey(
					entity = Person.class,
					parentColumns = "personKey",
					childColumns = "personKey",
					onDelete = CASCADE))
	public static class Data {
		@PrimaryKey
		private long personKey;
		private String office;

		@Ignore
		Data(Faculty faculty) {
			this.personKey = faculty.getPersonKey();
			this.office = faculty.getOffice();
		}

		Data() {
		}

		long getPersonKey() {
			return personKey;
		}

		void setPersonKey(long personKey) {
			this.personKey = personKey;
		}

		String getOffice() {
			return office;
		}

		void setOffice(String office) {
			this.office = office;
		}
	}


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
	@NonNull
	public String toString() {
		return super.toString() + " " + office;
	}
}
