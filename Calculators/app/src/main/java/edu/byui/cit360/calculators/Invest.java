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
	private EditText curPrinc, decAR, intYears, intPPY;
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

		curPrinc = (EditText)view.findViewById(R.id.invCurPrinc);
		decAR = (EditText)view.findViewById(R.id.invDecAR);
		intYears = (EditText)view.findViewById(R.id.invIntYears);
		intPPY = (EditText)view.findViewById(R.id.invIntPPY);
		curFV = (TextView)view.findViewById(R.id.invCurFV);
		view.findViewById(R.id.invBtnFV).setOnClickListener(new Compute());
		view.findViewById(R.id.invBtnClear).setOnClickListener(new Clear());
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		curPrinc.requestFocus();
	}

	private class Compute implements OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				double a = Calculators.getCur(curPrinc);
				double ar = Calculators.getDec(decAR) / 100.0;
				int y = Calculators.getInt(intYears);
				int ppy = Calculators.getInt(intPPY);
				double r = ar / ppy;
				int n = y * ppy;
				double fv = a * Math.pow(1 + r, n);
				fv = Math.round(fv * 100.0) / 100.0;
				curFV.setText(curFmtr.format(fv));
			}
			catch (Exception ex) {
				Log.e(Calculators.TAG, "exception", ex);
			}
		}
	}

	private class Clear implements OnClickListener {
		@Override
		public void onClick(View view) {
			curPrinc.getText().clear();
			decAR.getText().clear();
			intYears.getText().clear();
			intPPY.getText().clear();
			curFV.setText("");
		}
	}
}
