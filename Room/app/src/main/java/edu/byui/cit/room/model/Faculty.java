package edu.byui.cit.room.model;

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
}

//public class Faculty extends Person {
//	@Relation(parentColumn = "personKey", entityColumn = "personKey")
//	FacultyData facultyData;
//
//	Faculty() {
//	}
//
//	FacultyData getFacultyData() {
//		return facultyData;
//	}
//
//	void setFacultyData(FacultyData facultyData) {
//		this.facultyData = facultyData;
//	}
//
//
//	@Ignore
//	public Faculty(String firstName, String lastName, String office) {
//		super(firstName, lastName);
//		facultyData = new FacultyData(office);
//	}
//
//	public String getOffice() {
//		return facultyData.getOffice();
//	}
//
//	public void setOffice(String office) {
//		facultyData.setOffice(office);
//	}
//}
