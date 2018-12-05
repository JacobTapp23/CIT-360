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
