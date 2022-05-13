package edu.byui.cit.worktime.model;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

public class ProjectTest {

    @Test
    public void testProjectClass() {
        Project p1 = new Project("Write Essay", "English 301");
        Project p2 = new Project("Play Zelda", "fun,fun,fun");
        Project p3 = new Project("Write Essay", "English 301");

        assertEquals(0, p1.getProjectKey());
        assertEquals("Write Essay", p1.getTitle());
        assertEquals("English 301", p1.getDescription());

        assertEquals(false, p1.equals(p2));

        assertTrue(p1.equals(p3));

        //Get the application context so that we can use it to connect to the database.
        Context appCtx = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // Connect to the database
        AppDatabase db = AppDatabase.getInstance(appCtx);

        //Get the project data access object ProjectDAO
        ProjectDAO pdao = db.getProjectDAO();

        // 1. Delete all rows from a table
        pdao.deleteAll();

        // 2. Verify that the count of that table is 0
        assertEquals(0, pdao.count());

        // 3. Insert two rows into that table
        pdao.insert(p1);
        pdao.insert(p2);

        // 4. Verify that the count is 2
        assertEquals(2, pdao.count());

        // 5. Retrieve inserted rows
        Project stored1 = pdao.getProjectByProjectKey(p1.getProjectKey());
        Project stored2 = pdao.getProjectByProjectKey(p2.getProjectKey());

        // 6. Verify that the data in the retrieved rows is exactly
        // the same as the data that was inserted into the rows
        assertEquals(p2, stored2);
        assertEquals(p1, stored1);

        // 7. Update one of the inserted rows
        p1.setDescription("English 320");
        pdao.update(p1);

        // 8. Verify that the count is still 2
        assertEquals(2, pdao.count());

        // 9. Retrieve updated row
        Project updated1 = pdao.getProjectByProjectKey(p1.getProjectKey());

        // 10. Verify that the data in the retrieved row is exactly
        // the same as the data that was updated into the row
        assertEquals(p1, updated1);

        // 11. Retrieve the row that was not updated
        Project notupdated1 = pdao.getProjectByProjectKey(p2.getProjectKey());

        // 12. Verify that the data in the non-updated row is correct
        assertEquals("Play Zelda", p2.getTitle());

        // 13. Delete one of the rows
        pdao.delete(p2);

        // 14. Verify that the count is 1
        assertEquals(1, pdao.count());

        // 15. Retrieve the row that is still in the database
        pdao.getAll();

        // 16. Verify that the remaining row contains the correct data
        assertEquals("English 320", p1.getDescription());

        // 17. Delete the remaining row
        pdao.delete(p1);

        // 18. Verify that the count is 0
        assertEquals(0, pdao.count());
    }
}