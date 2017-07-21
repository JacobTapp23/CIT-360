package edu.byui.cit360.units;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Named {
	String getName();
	void setLocalizedName(String name);
	String toString();
}


abstract class Container<P extends Named> implements Named {
	private final String name;
	private String localName;
	private final Map<String, P> all;

	Container(String name) {
		this.name = name;
		this.localName = name;
		this.all = new HashMap<>();
	}

	Container<P> add(P p) {
		all.put(p.getName(), p);
		return this;
	}

	@Override
	public String getName() { return name; }

	@Override
	public void setLocalizedName(String name) {
		this.localName = name;
	}

	@Override
	public String toString() { return localName; }

	public P get(String name) {
		return all.get(name);
	}

	public List<P> get(Iterable<String> names) {
		List<P> list = new ArrayList<>();
		for (String name : names) {
			P p = all.get(name);
			if (p != null) {
				list.add(p);
			}
		}
		return list;
	}

	public List<P> getExcept(Collection<String> names) {
		List<P> list = new ArrayList<>();
		for (String name : all.keySet()) {
			P p = all.get(name);
			if (! names.contains(name)) {
				list.add(p);
			}
		}
		return list;
	}

	public Collection<P> getAll() {
		return all.values();
	}
}
