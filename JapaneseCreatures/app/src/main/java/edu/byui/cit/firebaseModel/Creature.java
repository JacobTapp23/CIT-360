package edu.byui.cit.firebaseModel;

import com.google.firebase.database.Exclude;


public class Creature {
	@Exclude
	private String key;

	private String name;
	private String type;

	@SuppressWarnings("unused")  // Used by firebase
	public Creature() {
	}

	public Creature(String key) {
		this.key = key;
	}

	public Creature(String name, String type) {
		this.name = name;
		this.type = type;
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
