package edu.byui.cit360.calculators;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.byui.cit360.units.Quantity;
import edu.byui.cit360.units.Unit;
import edu.byui.cit360.units.World;

public class ComparePrices extends CalcFragment {
	private static class Product {
		EditText curPrice, decQuant;
		Spinner spinUnits;
		TextView txtPer;
	}

	private Spinner unitsType;
	private Product[] products;
	private NumberFormat curFmtr;

	public ComparePrices() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		products = new Product[3];
		for (int i = 0;  i < products.length;  ++i) {
			products[i] = new Product();
		}
		curFmtr = NumberFormat.getCurrencyInstance();

		// Initialize the localized name for all supported physical
		// quantities and corresponding units, so that the localized
		// name can appear in the units drop down lists.
		Resources res = getResources();
		String[] supported = res.getStringArray(R.array.supportedQuantities);
		World world = World.getInstance();
		List<Quantity> physQuants = world.get(Arrays.asList(supported));
		for (Quantity quant : physQuants) {
			try {
				String name = quant.getName();
				String localName = res.getString(getID(R.string.class, name));
				quant.setLocalizedName(localName);

				// Get the list of desired units for this physical quantity.
				String[] unitsNames = res.getStringArray(getID(R.array.class, name));
				List<Unit> units = quant.get(Arrays.asList(unitsNames));
				for (Unit unit : units) {
					try {
						name = unit.getName();
						localName = res.getQuantityString(getID(R.plurals.class, name), 1);
						unit.setLocalizedName(localName);
					}
					catch (Exception ex) {
						Log.e(Calculators.TAG, "exception", ex);
					}
				}
			}
			catch (Exception ex) {
				Log.e(Calculators.TAG, "exception", ex);
			}
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment.
		View view = inflater.inflate(R.layout.compare_prices, container, false);

		// Create an adapter for the units type spinner.
		Activity ctx = getActivity();
		ArrayAdapter<Quantity> adapter =
			new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item);

		// Add an empty item at the beginning of the adapter.
		adapter.add(new Quantity(""));

		Resources res = getResources();
		World world = World.getInstance();
		String[] supported = res.getStringArray(R.array.supportedQuantities);
		List<Quantity> physQuants = world.get(Arrays.asList(supported));
		adapter.addAll(physQuants);

		// Specify the layout to use when the list of choices appears.
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Get a reference to the units type spinner.
		unitsType = (Spinner)view.findViewById(R.id.cmpSpinUnitsType);

		// Apply the adapter to the spinner.
		unitsType.setAdapter(adapter);
		unitsType.setOnItemSelectedListener(new ChangeUnitsType());

		// Get references to the user interface components for each product.
		Class clss = R.id.class;
		Compare compare = new Compare();
		for (int i = 0;  i < products.length;  ++i) {
			Product prod = products[i];
			int which = i + 1;
			try {
				prod.curPrice = (EditText)view.findViewById(getID(clss, "cmpCurPrice" + which));
				prod.decQuant = (EditText)view.findViewById(getID(clss, "cmpDecQuant" + which));
				prod.spinUnits = (Spinner)view.findViewById(getID(clss, "cmpSpinUnits" + which));
				prod.txtPer = (TextView)view.findViewById(getID(clss, "cmpTxtPer" + which));

				prod.curPrice.addTextChangedListener(compare);
				prod.decQuant.addTextChangedListener(compare);
				prod.spinUnits.setOnItemSelectedListener(compare);
			}
			catch (Exception ex) {
				Log.e(Calculators.TAG, "exception", ex);
			}
		}

		view.findViewById(R.id.cmpBtnCompute).setOnClickListener(compare);
		view.findViewById(R.id.cmpBtnClear).setOnClickListener(new Clear());
		return view;
	}


	@Override
	public void onStart() {
		super.onStart();
		products[0].curPrice.requestFocus();
	}


	private class ChangeUnitsType implements OnItemSelectedListener {
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			for (Product prod : products) {
				prod.txtPer.setText("nothing selected");
			}
		}

		@Override
		public void onItemSelected(
			AdapterView<?> parent, View view, int pos, long id) {
			try {
				Quantity physQuant = (Quantity)parent.getItemAtPosition(pos);
				String quantName = physQuant.getName();

				ArrayAdapter<Unit> adapter;
				if (quantName.equals("")) {
					adapter = null;
				}
				else {
					Activity ctx = getActivity();
					Resources res = getResources();
					int arrayID = getID(R.array.class, quantName);
					String[] unitsNames = res.getStringArray(arrayID);

					adapter = new ArrayAdapter<>(ctx,
						android.R.layout.simple_spinner_item,
						physQuant.get(Arrays.asList(unitsNames)));
					adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
				}
				for (Product prod : products) {
					// Apply the adapter to the spinner
					prod.spinUnits.setAdapter(adapter);
				}
			}
			catch (Exception ex) {
				Log.e(Calculators.TAG, "exception", ex);
			}
		}
	}


	private class Compare implements OnClickListener, TextWatcher, OnItemSelectedListener {
		@Override
		public void onClick(View view) {
			compare();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			compare();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			compare();
		}

		private void compare() {
			clearResults();

			Unit general = null;
			Quantity physQuant = (Quantity)unitsType.getSelectedItem();
			String quantName = physQuant.getName();
			if (! quantName.equals("")) {
				// If the user has selected a physical quantity and
				// units, then choose the units to use for comparison.
				ArrayList<Unit> unitsList = new ArrayList<>(products.length);
				for (Product prod : products) {
					try {
						// Try to parse the user input numbers. If they
						// don't correctly parse, then the exception that
						// gets thrown will prevent the corresponding
						// units, if any, from being added to the list.
						Calculators.getCur(prod.curPrice);
						Calculators.getDec(prod.decQuant);

						Unit units = (Unit)prod.spinUnits.getSelectedItem();
						if (!unitsList.contains(units)) {
							unitsList.add(units);
						}
					}
					catch (Exception ex) {
						// Do nothing.
					}
				}

				if (unitsList.size() > 0) {
					// Select the units to use for comparison.
					Collections.sort(unitsList);
					int index = (unitsList.size() - 1) / 2;
					general = unitsList.get(index);
				}
			}

			Product best = null;
			double min = Double.MAX_VALUE;
			String per = getResources().getString(R.string.per);
			for (Product prod : products) {
				try {
					double price = Calculators.getCur(prod.curPrice);
					double quant = Calculators.getDec(prod.decQuant);
					if (general != null) {
						Unit units = (Unit)prod.spinUnits.getSelectedItem();
						quant = Quantity.convert(general, quant, units);
					}
					double ratio = price / quant;
					if (ratio < min) {
						min = ratio;
						best = prod;
					}
					String output = curFmtr.format(ratio);
					if (general != null) {
						output += " " + per + " " + general;
					}
					prod.txtPer.setText(output);
				}
				catch (Exception ex) {
					Log.e(Calculators.TAG, "exception", ex);
				}
			}

			if (best != null) {
				int color = getResources().getColor(R.color.colorBest, null);
				best.txtPer.setBackgroundColor(color);
			}
		}
	}


	private class Clear implements OnClickListener {
		@Override
		public void onClick(View view) {
			String physName = unitsType.getSelectedItem().toString();
			boolean hasUnits = !physName.equals("");
			for (Product prod : products) {
				prod.curPrice.getText().clear();
				prod.decQuant.getText().clear();
				if (hasUnits) {
					prod.spinUnits.setSelection(0);
				}
			}
			clearResults();
		}
	}


	private void clearResults() {
		for (Product prod : products) {
			prod.txtPer.setText("");
			prod.txtPer.setBackground(null);
		}
	}


	// Uses reflection to get an id from R.id, R.array, R.plurals, etc.
	private int getID(Class clss, String name)
		throws NoSuchFieldException, IllegalAccessException {
		Field field = clss.getDeclaredField(name);
		return field.getInt(null);
	}
}
