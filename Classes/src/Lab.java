/* Your boss has asked you to write molecular simulation software, no
small task.
1. Open the IntelliJ IDE which you should have previously installed.
2. Create a new Java Project named Lab inside your bitbucket repository.
3. Write a Java class named "Particle".
   a. This class must have the following private properties:
      i. mass - type double
      ii. charge - type double
   b. The Particle class must have a constructor that accepts and sets values
      for each of the three properties.
   c. The Particle class must have getter and setter methods for each of the
      properties.
   d. The Particle class must have a toString method that overrides the
      toString method in class Object.
4. Write a Java class named "Proton".
   a. This class must inherit from the Particle class.
   b. The Proton class must have a constructor that takes no parameters
      that calls the Particle constructor and passes 1.673 and 1.602 as the
      mass and charge.
   c. The Proton class must have a "toString" method that takes no
      parameters and returns a String description of the Proton. The
      description must include the name of the class, and inherited
      properties, and the value for each of those properties. For example,
      "proton mass= ... charge="
   d. The Proton class must have a toString method that overrides the
      toString method in class Particle.
5. Write a Java class named "Neutron".
   a. This class must inherit from the Particle class.
   b. The Neutron class must have a constructor that takes no parameters
      and calls the Particle constructor and passes 1.675 and 0 as the
      mass and charge.
   c. The Neutron class must have a "toString" method that takes no
      parameters and returns a String description of the Neutron. The
      description must include the name of the class, and inherited
      properties and the value for each of those properties. For example,
      "neutron mass= ... charge="
   d. The Neutron class must have a toString method that overrides the
      toString method in class Particle.
6. Write a Java class named "Electron".
   a. This class must inherit from the Particle class.
   b. The Electron class must have a constructor that takes no parameters
      that calls the Particle constructor and passes 9.109 and -1.602 as the
      mass and charge.
   c. The Electron class must have a "toString" method that takes no
      parameters and returns a String description of the Electron. The
      description must include the name of the class, and inherited
      properties, and the value for each of those properties. For example,
      "electron mass= ... charge="
   d. The Electron class must have a toString method that overrides the
      toString method in class Particle.
7. Write a class named "Atom".
   a. This class must have four private attributes:
      i. name - type String
      ii. protons - an array of Proton objects
      iii. neutrons - an array of Neutron objects
      iv. electrons - an array of Electron objects
   b. class Atom must have a constructor that initializes the four attributes.
   c. class Atom must have a toString method that overrides the toString
      method in class Object.
8. Write a class named "Molecule".
   a. This class must have two private attributes:
      i. name - type String
      ii. atoms - an array of Atom objects
   b. class Molecule must have a constructor that initializes the two
      attributes.
   c. class Atom must have a toString method that overrides the toString
      method in class Object.
9. Write a class named "Lab".
   a. class Lab must contain the main method. The main method must do
   the following:
      i. Create a water Molecule that has two hydrogen Atoms and one
         Oxygen atom.
      ii. Use the toString methods to print the contents of the water
         Molecule to the console.
 */
public class Lab {
	public static void main(String[] args) {
		Molecule water = new Molecule("water", 3);
		water.addAtom(0, new Atom("hydrogen", 1, 1,1));
		water.addAtom(1, new Atom("oxygen", 8, 8,8));
		water.addAtom(2, new Atom("hydrogen", 1, 1,1));
		System.out.println(water);
	}
}

/* A class is a template or pattern or blueprint for creating objects.
 * Each object created from the Particle class will have two attributes
 * named mass and charge.
 */
class Particle {
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

		this.protons = new Proton[protons];
		for (int i = 0;  i < this.protons.length;  ++i) {
			this.protons[i] = new Proton();
		}

		this.neutrons = new Neutron[neutrons];
		for (int i = 0;  i < this.neutrons.length;  ++i) {
			this.neutrons[i] = new Neutron();
		}

		this.electrons = new Electron[electrons];
		for (int i = 0;  i < this.electrons.length;  ++i) {
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
