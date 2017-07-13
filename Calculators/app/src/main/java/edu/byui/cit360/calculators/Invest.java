package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class Invest extends CalcFragment {
	private EditText numPrinc, numAR, numYears, numPPY;
	private TextView curFV;
	private NumberFormat curFmtr;

	public Invest() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		curFmtr = NumberFormat.getCurrencyInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.invest, container, false);

		numPrinc = (EditText)view.findViewById(R.id.numPrinc);
		numAR = (EditText)view.findViewById(R.id.numAR);
		numYears = (EditText)view.findViewById(R.id.numYears);
		numPPY = (EditText)view.findViewById(R.id.numPPY);
		curFV = (TextView)view.findViewById(R.id.txtFV);
		view.findViewById(R.id.btnCompute).setOnClickListener(new Compute());
		view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
		return view;
	}

	private class Compute implements OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				double a = Calculators.getCur(numPrinc);
				double ar = Calculators.getDec(numAR) / 100.0;
				int y = Calculators.getInt(numYears);
				int ppy = Calculators.getInt(numPPY);
				double r = ar / ppy;
				int n = y * ppy;
				double fv = a * Math.pow(1 + r, n);
				fv = Math.round(fv * 100.0) / 100.0;
				curFV.setText(curFmtr.format(fv));
			}
			catch (Exception ex) {
				String name = getResources().getString(R.string.appName);
				Log.e(name, "exception", ex);
			}
		}
	}

	private class Clear implements OnClickListener {
		@Override
		public void onClick(View view) {
			numPrinc.getText().clear();
			numAR.getText().clear();
			numYears.getText().clear();
			numPPY.getText().clear();
			curFV.setText("");
		}
	}
}
