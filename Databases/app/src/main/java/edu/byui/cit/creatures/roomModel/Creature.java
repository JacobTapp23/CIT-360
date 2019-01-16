package edu.byui.cit.creatures.roomModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Creature {
	@PrimaryKey(autoGenerate = true)
	private long key;

	private String name;
	private String type;


	@Ignore
	public Creature(long key) {
		this.key = key;
	}

	@Ignore
	public Creature(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public Creature(long key, String name, String type) {
		this.key = key;
		this.name = name;
		this.type = type;
	}

	public long getKey() {
		return key;
	}

	void setKey(long key) {
		this.key = key;
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

	@Override
	public boolean equals(Object obj) {
		boolean eq = false;
		if (obj instanceof Creature) {
			Creature other = (Creature)obj;
			eq = this.key == other.key &&
					this.name.equals(other.name) &&
					this.type.equals(other.type);
		}
		return eq;
	}

	@Override
	public String toString() {
		return key + " " + " " + name + " " + type;
	}
}
