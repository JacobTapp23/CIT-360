package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ShoeSize extends CalcFragment {
	private Map<Float, Float> inchesFromUSA;
	private EditText decUSA, decMetric;
	private NumberFormat decFmtr;

	public ShoeSize() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inchesFromUSA = new HashMap<>();
		inchesFromUSA.put(6F, (9 + 1 / 4F));
		inchesFromUSA.put(6.5F, (9 + 1 / 2F));
		inchesFromUSA.put(7F, (9 + 5 / 8F));
		inchesFromUSA.put(7.5F, (9 + 3 / 4F));
		inchesFromUSA.put(8F, (9 + 15 / 16F));
		inchesFromUSA.put(8.5F, (10 + 1 / 8F));
		inchesFromUSA.put(9F, (10 + 1 / 4F));
		inchesFromUSA.put(9.5F, (10 + 7 / 16F));
		inchesFromUSA.put(10F, (10 + 9 / 16F));
		inchesFromUSA.put(10.5F, (10 + 3 / 4F));
		inchesFromUSA.put(11F, (10 + 15 / 16F));
		inchesFromUSA.put(11.5F, (11 + 1 / 8F));
		inchesFromUSA.put(12F, (11 + 1 / 4F));
		inchesFromUSA.put(13F, (11 + 9 / 16F));
		inchesFromUSA.put(14F, (11 + 7 / 8F));
		inchesFromUSA.put(15F, (12 + 3 / 16F));
		inchesFromUSA.put(16F, (12 + 1 / 2F));

		decFmtr = NumberFormat.getInstance();
		decFmtr.setMinimumFractionDigits(0);
		decFmtr.setMaximumFractionDigits(1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.shoe_size, container, false);

		decUSA = (EditText) view.findViewById(R.id.decUSA);
		decMetric = (EditText) view.findViewById(R.id.decMetric);
		view.findViewById(R.id.btnCompute).setOnClickListener(new Compute());
		view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
		return view;
	}

	private class Compute implements OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				String usa = decUSA.getText().toString();
				String metric = decMetric.getText().toString();
				if (usa.length() > 0) {
					float u = (float) Calculators.getDec(decUSA);
					float inches = inchesFromUSA.get(u);
					float millis = inches * 2.54F;
					decMetric.setText(decFmtr.format(millis));
				}
				else if (metric.length() > 0) {

				}
			}
			catch (Exception ex) {
				String name = getResources().getString(R.string.appName);
				Log.e(name, "exception", ex);
			}
		}
	}

	private class Clear implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			decUSA.getText().clear();
			decMetric.getText().clear();
		}
	}
}
