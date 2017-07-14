package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.NumberFormat;

public class ComparePrices extends CalcFragment {
	private static class Product {
		EditText curPrice, decQuant;
		TextView curPer;
	}

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.compare_prices, container, false);

		for (int i = 0;  i < products.length;  ++i) {
			Product prod = products[i];
			int which = i + 1;
			try {
				prod.curPrice = (EditText)view.findViewById(getID("cmpCurPrice" + which));
				prod.decQuant = (EditText)view.findViewById(getID("cmpDecQuant" + which));
				prod.curPer = (TextView)view.findViewById(getID("cmpCurPer" + which));
			}
			catch (Exception ex) {
				Log.e(Calculators.TAG, "exception", ex);
			}
		}

		view.findViewById(R.id.cmpBtnCompute).setOnClickListener(new Compare());
		view.findViewById(R.id.cmpBtnClear).setOnClickListener(new Clear());
		return view;
	}

	// Uses reflection to get an id from R.id
	private int getID(String name) throws NoSuchFieldException, IllegalAccessException {
		Field field = R.id.class.getDeclaredField(name);
		return field.getInt(null);
	}

	@Override
	public void onStart() {
		super.onStart();
		products[0].curPrice.requestFocus();
	}

	private class Compare implements OnClickListener {
		@Override
		public void onClick(View view) {
			clearResults(view);

			double min = Double.MAX_VALUE;
			Product best = null;
			for (Product prod : products) {
				try {
					double price = Calculators.getCur(prod.curPrice);
					double quant = Calculators.getDec(prod.decQuant);
					double per = price / quant;
					prod.curPer.setText(curFmtr.format(per));
					if (per < min) {
						min = per;
						best = prod;
					}
				}
				catch (Exception ex) {
					Log.e(Calculators.TAG, "exception", ex);
				}
			}

			if (best != null) {
				int color = getResources().getColor(R.color.colorBest, null);
				best.curPer.setBackgroundColor(color);
			}
		}
	}

	private class Clear implements OnClickListener {
		@Override
		public void onClick(View view) {
			for (Product prod : products) {
				prod.curPrice.getText().clear();
				prod.decQuant.getText().clear();
			}
			clearResults(view);
		}
	}

	private void clearResults(View view) {
		for (Product prod : products) {
			prod.curPer.setText("");
			prod.curPer.setBackground(null);
		}
	}
}
