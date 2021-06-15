package edu.byui.cit.room.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "Faculty",
		foreignKeys = @ForeignKey(
				entity = Person.class,
				parentColumns = "personKey",
				childColumns = "personKey",
				onDelete = CASCADE))
public class FacultyData {
	@PrimaryKey
	private long personKey;

	private String office;

	FacultyData() {
	}

	@Ignore
	FacultyData(Faculty faculty) {
		this.personKey = faculty.getPersonKey();
		this.office = faculty.getOffice();
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
