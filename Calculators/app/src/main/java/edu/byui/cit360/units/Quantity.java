package edu.byui.cit360.units;

public class Quantity extends Container<Unit> {
	public Quantity(String name) {
		super(name);
	}

	@Override
	Quantity add(Unit unit) {
		super.add(unit);
		return this;
	}

	public double convert(String to, double quant, String from) {
		return Quantity.convert(get(to), quant, get(from));
	}

	public static double convert(Unit to, double quant, Unit from) {
		double facFrom = from.getFactor();
		double facTo   = to.getFactor();
		return quant * (facTo / facFrom);
	}
}
