package edu.byui.cit.room.model;

import android.content.Context;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FacultyTest {
	@Test
	public void testFaculty() {
		Context appCtx = InstrumentationRegistry.getInstrumentation().getTargetContext();
		AppDatabase db = AppDatabase.getInstance(appCtx);
		PersonDAO pdao = db.getPersonDAO();
		FacultyDAO fdao = db.getFacultyDAO();

		fdao.deleteAll();
		long persons = pdao.count();
		long facultys = 0;
		assertEquals(facultys, fdao.count());

		final String firstName = "Sam";
		final String lastName = "Snead";
		final String office = "STC 310S";

		Faculty faculty1 = new Faculty(firstName, lastName, office);
		assertEquals(firstName, faculty1.getFirstName());
		assertEquals(lastName, faculty1.getLastName());
		assertEquals(office, faculty1.getOffice());

		fdao.insert(faculty1);
		persons++;
		facultys++;
		assertEquals(persons, pdao.count());
		assertEquals(facultys, fdao.count());
		assertTrue(faculty1.getPersonKey() > 0);

		Faculty faculty2 = new Faculty("Teresa", "Thompson", "Taylor 219");
		fdao.insert(faculty2);
		persons++;
		facultys++;
		assertEquals(persons, pdao.count());
		assertEquals(facultys, fdao.count());
		assertTrue(faculty2.getPersonKey() > 0);

		Faculty stored1 = fdao.getFacultyByKey(faculty1.getPersonKey());
		Faculty stored2 = fdao.getFacultyByKey(faculty2.getPersonKey());
		assertEquals(faculty1, stored1);
		assertEquals(faculty2, stored2);

		final long personKey = faculty1.getPersonKey();
		assertTrue(personKey > 0);
		final String newName = "Aaron";
		final String newOffice = "Austin 110";
		faculty1.setFirstName(newName);
		faculty1.setOffice(newOffice);
		assertEquals(personKey, faculty1.getPersonKey());
		assertEquals(newName, faculty1.getFirstName());
		assertEquals(newOffice, faculty1.getOffice());

		fdao.update(faculty1);
		assertEquals(persons, pdao.count());
		assertEquals(facultys, fdao.count());
		assertEquals(personKey, faculty1.getPersonKey());

		Faculty updated1 = fdao.getFacultyByKey(faculty1.getPersonKey());
		stored2 = fdao.getFacultyByKey(faculty2.getPersonKey());
		assertEquals(faculty1, updated1);
		assertEquals(faculty2, stored2);

		fdao.delete(faculty1);
		persons--;
		facultys--;
		assertEquals(persons, pdao.count());
		assertEquals(facultys, fdao.count());

		stored2 = fdao.getFacultyByKey(faculty2.getPersonKey());
		assertEquals(faculty2, stored2);

		fdao.delete(faculty2);
		persons--;
		facultys--;
		assertEquals(persons, pdao.count());
		assertEquals(facultys, fdao.count());
	}
}