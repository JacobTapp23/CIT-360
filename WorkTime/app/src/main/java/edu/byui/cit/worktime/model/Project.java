package edu.byui.cit.worktime.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Project {
    @PrimaryKey(autoGenerate = true)
    private long projectKey;

    private String title;
    private String description;

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getProjectKey() {
        return projectKey;
    }

    void setProjectKey(long projectKey) {
        this.projectKey = projectKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return projectKey == project.projectKey &&
                title.equals(project.title) &&
                description.equals(project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectKey, title, description);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectKey=" + projectKey +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}