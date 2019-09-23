package edu.byui.cit.creatures.roomModel;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;


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
		boolean eq = (this == obj);
		if (!eq && obj != null && getClass() == obj.getClass()) {
			Creature other = (Creature)obj;
			eq = this.key == other.key &&
					this.name.equals(other.name) &&
					this.type.equals(other.type);
		}
		return eq;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, name, type);
	}

	@Override
	public String toString() {
		return key + " " + " " + name + " " + type;
	}
}
