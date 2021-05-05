package edu.byui.cit.worktime.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(foreignKeys = @ForeignKey(
        entity = Project.class,
        parentColumns = "projectKey",
        childColumns = "projectKey",
        onDelete = ForeignKey.CASCADE))
public class Session {
    @PrimaryKey(autoGenerate = true)
    private long sessionKey;

    private long projectKey;
    private String description;
    private Date start;
    private Date end;

    public Session(long projectKey, String description, Date start, Date end) {
        this.projectKey = projectKey;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public long getSessionKey() {
        return sessionKey;
    }

    void setSessionKey(long sessionKey) {
        this.sessionKey = sessionKey;
    }

    public long getProjectKey() {
        return projectKey;
    }

    void setProjectKey(long projectKey) {
        this.projectKey = projectKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Session session = (Session) obj;
        return sessionKey == session.sessionKey &&
                projectKey == session.projectKey &&
                Objects.equals(description, session.description) &&
                Objects.equals(start, session.start) &&
                Objects.equals(end, session.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionKey, projectKey, description, start, end);
    }
    @Override
            public String toString() {
        return "Session{" +
                "sessionKey=" + sessionKey +
                ", projectKey" + projectKey +
                ", description" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';

        }

}
