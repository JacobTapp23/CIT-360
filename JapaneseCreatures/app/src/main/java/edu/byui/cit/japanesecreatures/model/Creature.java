package edu.byui.cit.japanesecreatures.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Creature {
    @PrimaryKey(autoGenerate=true)
    private long creatureId;

    private String name;
    private String type;

	public Creature(long id) {
		this.creatureId = id;
	}

    public Creature(String name, String type) {
        this.name = name;
        this.type = type;
    }

	public Creature(long id, String name, String type) {
		this.creatureId = id;
		this.name = name;
		this.type = type;
	}

    public long getCreatureId() {
        return creatureId;
    }

    public void setCreatureId(long creatureId) {
        this.creatureId = creatureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
