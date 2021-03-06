package edu.byui.cit.worktime.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class ProjectDAO {

    @Query("SELECT COUNT(*) FROM Project")
    public abstract long count();

    @Query("SELECT * FROM Project")
    public abstract List<Project> getAll();

    @Query("SELECT * FROM Project WHERE projectKey = :projectKey")
    public abstract Project getProjectByProjectKey(long projectKey);


    public void insert(Project proj) {
        long pk = realInsertAll(proj);
        proj.setProjectKey(pk);
    }

    @Insert
    abstract long realInsertAll(Project proj);

    @Update
    public abstract void update(Project proj);

    @Delete
    public abstract void delete(Project proj);

    @Query("DELETE FROM Project")
    public abstract void deleteAll();
}
