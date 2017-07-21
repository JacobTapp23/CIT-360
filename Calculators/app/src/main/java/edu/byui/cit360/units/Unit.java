package edu.byui.cit360.units;

import java.lang.annotation.Annotation;

public class Unit implements Named, Comparable<Unit> {
	private final Quantity quant;
	private final String name;
	private String localName;
	private final String abbrev;
	private final double factor;

	Unit(Quantity quant, String name, String abbrev, double factor) {
		this.quant = quant;
		this.name = name;
		this.localName = name;
		this.abbrev = abbrev;
		this.factor = factor;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setLocalizedName(String name) {
		this.localName = name;
	}

	public Quantity getQuantity() { return quant; }
	public String getAbbrev() { return abbrev; }
	double getFactor() { return factor; }

	@Override
	public int compareTo(Unit other) {
		return (int)Math.signum(other.factor - factor);
	}

	@Override
	public String toString() { return localName; }
}
