package edu.byui.cit.room.model;

import android.content.Context;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EmployeeTest {
	@Test
	public void testEmployee() {
		Context appCtx = InstrumentationRegistry.getInstrumentation().getTargetContext();
		AppDatabase db = AppDatabase.getInstance(appCtx);
		PersonDAO pdao = db.getPersonDAO();
		EmployeeDAO edao = db.getEmployeeDAO();

		edao.deleteAll();
		long persons = pdao.count();
		long employees = 0;
		assertEquals(employees, edao.count());

		final String firstName = "Blake";
		final String lastName = "Bonner";
		final String title = "Big Boss";

		Employee employee1 = new Employee(firstName, lastName, title);
		assertEquals(firstName, employee1.getFirstName());
		assertEquals(lastName, employee1.getLastName());
		assertEquals(title, employee1.getTitle());

		edao.insert(employee1);
		persons++;
		employees++;
		assertEquals(persons, pdao.count());
		assertEquals(employees, edao.count());
		assertTrue(employee1.getPersonKey() > 0);

		Employee employee2 = new Employee("Carl", "Clay", "Custodian");
		edao.insert(employee2);
		persons++;
		employees++;
		assertEquals(persons, pdao.count());
		assertEquals(employees, edao.count());
		assertTrue(employee2.getPersonKey() > 0);

		Employee stored1 = edao.getEmployeeByKey(employee1.getPersonKey());
		Employee stored2 = edao.getEmployeeByKey(employee2.getPersonKey());
		assertEquals(employee1, stored1);
		assertEquals(employee2, stored2);

		final long personKey = employee1.getPersonKey();
		assertTrue(personKey > 0);
		final String newName = "Bart";
		final String newTitle = "Bad Boss";
		employee1.setFirstName(newName);
		employee1.setTitle(newTitle);
		assertEquals(personKey, employee1.getPersonKey());
		assertEquals(newName, employee1.getFirstName());
		assertEquals(newTitle, employee1.getTitle());

		edao.update(employee1);
		assertEquals(persons, pdao.count());
		assertEquals(employees, edao.count());
		assertEquals(personKey, employee1.getPersonKey());

		Employee updated1 = edao.getEmployeeByKey(employee1.getPersonKey());
		stored2 = edao.getEmployeeByKey(employee2.getPersonKey());
		assertEquals(employee1, updated1);
		assertEquals(employee2, stored2);

		edao.delete(employee1);
		persons--;
		employees--;
		assertEquals(persons, pdao.count());
		assertEquals(employees, edao.count());

		stored2 = edao.getEmployeeByKey(employee2.getPersonKey());
		assertEquals(employee2, stored2);

		edao.delete(employee2);
		persons--;
		employees--;
		assertEquals(persons, pdao.count());
		assertEquals(employees, edao.count());
	}
}