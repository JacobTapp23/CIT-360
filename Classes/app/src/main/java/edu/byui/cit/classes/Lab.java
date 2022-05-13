package edu.byui.cit.classes;

/* This file contains classes that demonstrate inheritance, method
 * overriding, and creating objects. For the complete set of student
 * instructions, see the file instructions.txt
 */

public class Lab {
	public static void main(String[] args) {
		Molecule water = new Molecule("water", 3);
		water.addAtom(0, new Atom("hydrogen", 1, 1, 1));
		water.addAtom(1, new Atom("oxygen", 8, 8, 8));
		water.addAtom(2, new Atom("hydrogen", 1, 1, 1));
		System.out.println(water);
	}
}

/* A class is a template or pattern or blueprint for creating objects.
 * Each object created from the Particle class will have two attributes
 * named mass and charge.
 */
abstract class Particle {
	private double mass;
	private double charge;

	public Particle(double mass, double charge) {
		this.mass = mass;
		this.charge = charge;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getMass() {
		return this.mass;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getCharge() {
		return this.charge;
	}

	/* The @Override annotation means that the following method
	 * overrides a method with the same signature (name and parameter
	 * list) in the parent class. In class Particle, the toString method
	 * overrides the toString method in class Object.
	 */
	@Override
	public String toString() {
		return "mass " + getMass() + "  charge " + getCharge();
	}
}


/* Proton extends Particle means that class Proton inherits from
 * class Particle
 */
class Proton extends Particle {
	public Proton() {
		super(1.673, 1.602);
	}

	/* The toString method in class Proton overrides the toString
	 * method in class Particle. However, the toString method in class
	 * Proton also calls the method in class Particle. It does this
	 * with the super keyword.
	 */
	@Override
	public String toString() {
		return "proton: " + super.toString();
	}
}


class Neutron extends Particle {
	public Neutron() {
		super(1.675, 0);
	}

	@Override
	public String toString() {
		return "neutron: " + super.toString();
	}
}


class Electron extends Particle {
	public Electron() {
		super(9.109, -1.602);
	}

	@Override
	public String toString() {
		return "electron: " + super.toString();
	}
}


class Atom {
	private String name;
	private Proton[] protons;
	private Neutron[] neutrons;
	private Electron[] electrons;

	public Atom(String name, int protons, int neutrons, int electrons) {
		this.name = name;

		// Create the array of protons for this atom.
		this.protons = new Proton[protons];
		for (int i = 0; i < this.protons.length; ++i) {
			this.protons[i] = new Proton();
		}

		// Create the array of neutrons for this atom.
		this.neutrons = new Neutron[neutrons];
		for (int i = 0; i < this.neutrons.length; ++i) {
			this.neutrons[i] = new Neutron();
		}

		// Create the array of electrons for this atom.
		this.electrons = new Electron[electrons];
		for (int i = 0; i < this.electrons.length; ++i) {
			this.electrons[i] = new Electron();
		}
	}

	@Override
	public String toString() {
		return this.name + ": ("
				+ this.protons.length + ", "
				+ this.neutrons.length + ", "
				+ this.electrons.length + ")";
	}
}


class Molecule {
	private String name;
	private Atom[] atoms;

	public Molecule(String name, int numberOfAtoms) {
		this.name = name;
		this.atoms = new Atom[numberOfAtoms];
	}

	/** Adds one Atom to this Molecule. */
	public void addAtom(int index, Atom toAdd) {
		this.atoms[index] = toAdd;
	}

	@Override
	public String toString() {
		String s = this.name + ":\n";
		for (Atom atom : this.atoms) {
			s += "\t" + atom.toString() + "\n";
		}
		return s;
	}
}
