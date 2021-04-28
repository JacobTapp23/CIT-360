package edu.byui.cit.worktime.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class SessionDao {
    @Query("SELECT * FROM Session")
    public abstract List<Session> getAll();

    @Query("SELECT * FROM Session WHERE sessionKey = :sessionKey")
    public abstract Session getSessionBySessionKey(long sessionKey);
    
    @Insert
    public abstract void insert(Session sess);

    @Update
    public abstract void update(Session sess);
    
    @Delete
    public abstract void delete(Session sess);
    
    @Query("DELETE FROM Session")
    public abstract void deleteAll();
    }

