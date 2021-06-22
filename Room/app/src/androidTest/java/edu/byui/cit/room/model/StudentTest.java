package edu.byui.cit.room.model;

import android.content.Context;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;

import static edu.byui.cit.room.model.Student.floatEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StudentTest {
	@Test
	public void testFloatEquals() {
		assertEquals((int)Math.signum(-0F), 0);
		assertEquals((int)Math.signum(0F), 0);
		System.out.println(Math.ulp(-0F));
		System.out.println(Math.ulp(0F));

		float t = 0.2513478F;
		float ulp = Math.ulp(t);
		float[][] negatives = {
				{1.1F, -1.1F},
				{0, Float.NaN},
				{Float.NaN, 0},
				{Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY},
				{Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY},
				{0F, 0F - 3F * Math.ulp(0F)},
				{0F, 0F + 3F * Math.ulp(0F)},
				{t, t - 3F * ulp},
				{t, t + 3F * ulp},
		};
		for (float[] values : negatives) {
			float e = values[0];
			float f = values[1];
			System.out.println(e + ", " + f);
			assertFalse(floatEquals(e, f, 2));
		}

		float[][] positives = {
				{Float.NaN, Float.NaN},
				{-0F, 0F},
				{Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY},
				{Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY},
				{1F/3F, 1F/3F},
				{0F, 0F - Math.ulp(0F)},
				{0F, 0F + Math.ulp(0F)},
				{t, t - ulp},
				{t, t + ulp},
		};
		for (float[] values : positives) {
			float e = values[0];
			float f = values[1];
			System.out.println(e + ", " + f);
			assertTrue(floatEquals(e, f, 2));
		}
	}


	@Test
	public void testStudent() {
		Context appCtx = InstrumentationRegistry.getInstrumentation().getTargetContext();
		AppDatabase db = AppDatabase.getInstance(appCtx);
		PersonDAO pdao = db.getPersonDAO();
		StudentDAO sdao = db.getStudentDAO();

		sdao.deleteAll();
		long persons = pdao.count();
		long students = 0;
		assertEquals(students, sdao.count());

		final String firstName = "Julie";
		final String lastName = "Jackson";
		final float gpa = 3.82F;
		final float delta = 0.0001F;

		Student student1 = new Student(firstName, lastName, gpa);
		assertEquals(firstName, student1.getFirstName());
		assertEquals(lastName, student1.getLastName());
		assertEquals(gpa, student1.getGpa(), delta);

		sdao.insert(student1);
		persons++;
		students++;
		assertEquals(persons, pdao.count());
		assertEquals(students, sdao.count());
		assertTrue(student1.getPersonKey() > 0);

		Student student2 = new Student("Harold", "Hall", 3.5F);
		sdao.insert(student2);
		persons++;
		students++;
		assertEquals(persons, pdao.count());
		assertEquals(students, sdao.count());
		assertTrue(student2.getPersonKey() > 0);

		Student stored1 = sdao.getStudentByKey(student1.getPersonKey());
		Student stored2 = sdao.getStudentByKey(student2.getPersonKey());
		assertEquals(student1, stored1);
		assertEquals(student2, stored2);

		final long personKey = student1.getPersonKey();
		assertTrue(personKey > 0);
		final String newName = "Josie";
		final float newGpa = 2.7F;
		student1.setFirstName(newName);
		student1.setGpa(newGpa);
		assertEquals(personKey, student1.getPersonKey());
		assertEquals(newName, student1.getFirstName());
		assertEquals(newGpa, student1.getGpa(), delta);

		sdao.update(student1);
		assertEquals(persons, pdao.count());
		assertEquals(students, sdao.count());
		assertEquals(personKey, student1.getPersonKey());

		Student updated1 = sdao.getStudentByKey(student1.getPersonKey());
		stored2 = sdao.getStudentByKey(student2.getPersonKey());
		assertEquals(student1, updated1);
		assertEquals(student2, stored2);

		sdao.delete(student1);
		persons--;
		students--;
		assertEquals(persons, pdao.count());
		assertEquals(students, sdao.count());

		stored2 = sdao.getStudentByKey(student2.getPersonKey());
		assertEquals(student2, stored2);

		sdao.delete(student2);
		persons--;
		students--;
		assertEquals(persons, pdao.count());
		assertEquals(students, sdao.count());
	}
}
