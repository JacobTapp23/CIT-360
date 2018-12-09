package edu.byui.cit.firebaseModel;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;


public class Creature {
	@Exclude
	private String key;

	private String name;
	private String type;

	private Object whenCreated;
	private String creator;

	private Object whenUpdated;
	private String updater;


	@SuppressWarnings("unused")  // Used by firebase
	public Creature() {
		this.whenCreated = ServerValue.TIMESTAMP;
	}

	// Used for searching the list of creatures.
	public Creature(String key) {
		this.key = key;
	}

	// Used for creating a creature that will be sent to firebase.
	public Creature(String name, String type, String creator) {
		this.name = name;
		this.type = type;
		this.whenCreated = ServerValue.TIMESTAMP;
		this.creator = creator;
	}

	// Used for creating a creature that will be sent to firebase.
	public Creature(String name, String type,
			Object whenCreated, String creator, String updater) {
		this.name = name;
		this.type = type;
		this.whenCreated = whenCreated;
		this.creator = creator;
		this.whenUpdated = ServerValue.TIMESTAMP;
		this.updater = updater;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
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

	public Object getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(Object whenCreated) {
		this.whenCreated = whenCreated;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Object getWhenUpdated() {
		return whenUpdated;
	}

	public void setWhenUpdated(Object whenUpdated) {
		this.whenUpdated = whenUpdated;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equ = false;
		if (obj instanceof Creature) {
			Creature other = (Creature)obj;
			equ = this.key.equals(other.key);
		}
		return equ;
	}
}
