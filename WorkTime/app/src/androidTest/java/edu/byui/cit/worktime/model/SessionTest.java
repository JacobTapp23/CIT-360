package edu.byui.cit.worktime.model;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.util.Date;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

public class SessionTest {

    @Test
    public void testSessionClass() {
        Project p1 = new Project("Write Essay", "English 301");
        Project p2 = new Project("Play Zelda", "fun,fun,fun");
        Project p3 = new Project("Write Essay", "English 301");

        Date start1 = new Date(2021, 4, 23, 10,30);
        Date end1 = new Date(2021, 4, 23, 12, 30);
        Session s1 = new Session( p1.getProjectKey(), "Check for weird odor",
                start1, end1);
        Session s2 = new Session( p2.getProjectKey(), "Use remote control to turn up volume",
                start1, end1);
        Session s3 = new Session( p1.getProjectKey(), "Check for weird odor",
                start1, end1);

        assertEquals(0, s1.getProjectKey());
        assertEquals("Check for weird odor", s1.getDescription());
        assertEquals("Use remote control to turn up volume", s2.getDescription());
        assertEquals(false, s1.equals(s2));

        assertTrue(s1.equals(s3));

        //Get the application context so that we can use it to connect to the database.
        Context appCtx = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // Connect to the database
        AppDatabase db = AppDatabase.getInstance(appCtx);

        //Get the session data access object SessionDao
        SessionDao sdao = db.getSessionDAO();

        // 1. Delete all rows from a table
        sdao.deleteAll();

        // 2. Verify that the count of that table is 0
        assertEquals(0, sdao.count());

        // 3. Insert two rows into that table
        sdao.insert(s1);
        sdao.insert(s2);

        // 4. Verify that the count is 2
        assertEquals(1, sdao.count());
        assertEquals(2, sdao.count());

        // 5. Retrieve inserted rows
        Session stored1 = sdao.getSessionBySessionKey(s1.getProjectKey());
        Session stored2 = sdao.getSessionBySessionKey(s2.getProjectKey());

        // 6. Verify that the data in the retrieved rows is exactly
        // the same as the data that was inserted into the rows
        assertEquals(s2, stored2);
        assertEquals(s1, stored1);

        // 7. Update one of the inserted rows
        s1.setDescription("Check for Strange Smell");
        sdao.update(s1);

        // 8. Verify that the count is still 2
        assertEquals(2, sdao.count());

        // 9. Retrieve updated row
        Session updated1 = sdao.getSessionBySessionKey(s1.getProjectKey());

        // 10. Verify that the data in the retrieved row is exactly
        // the same as the data that was updated into the row
        assertEquals(s1, updated1);

        // 11. Retrieve the row that was not updated
        Session notupdated1 = sdao.getSessionBySessionKey(s2.getProjectKey());

        // 12. Verify that the data in the non-updated row is correct
        assertEquals("Use remote control to turn up volume",s2.getDescription());

        // 13. Delete one of the rows
        sdao.delete(s2);

        // 14. Verify that the count is 1
        assertEquals(1, sdao.count());

        // 15. Retrieve the row that is still in the database
        sdao.getAll();

        // 16. Verify that the remaining row contains the correct data
        assertEquals("Check for Strange Smell",s1.getDescription());

        // 17. Delete the remaining row
        sdao.delete(s1);

        // 18. Verify that the count is 0
        assertEquals(0, sdao.count());
    }
}