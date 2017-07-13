package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class DistPerVol extends CalcFragment {
	private NumberFormat distFmtr, dpvFmtr;
	private EditText decBegin, decEnd, decDist, decVol;
	private TextView numDistPerVol;

	public DistPerVol() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		distFmtr = NumberFormat.getInstance();
		dpvFmtr = NumberFormat.getInstance();
		distFmtr.setMinimumFractionDigits(0);
		distFmtr.setMaximumFractionDigits(1);
		dpvFmtr.setMinimumFractionDigits(1);
		dpvFmtr.setMaximumFractionDigits(1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.dist_per_vol, container, false);

		decBegin = (EditText)view.findViewById(R.id.decBegin);
		decEnd = (EditText)view.findViewById(R.id.decEnd);
		decDist = (EditText)view.findViewById(R.id.decDist);
		decVol = (EditText)view.findViewById(R.id.decVol);
		numDistPerVol = (TextView)view.findViewById(R.id.numDistPerVol);

		TextWatcher watcher = new Distance();
		decBegin.addTextChangedListener(watcher);
		decEnd.addTextChangedListener(watcher);

		view.findViewById(R.id.btnCompute).setOnClickListener(new Compute());
		view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		decBegin.requestFocus();
	}

	private class Distance implements TextWatcher {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			try {
				String begin = decBegin.getText().toString().trim();
				String end = decEnd.getText().toString().trim();
				if (begin.length() > 0 && end.length() > 0) {
					double b = Calculators.getDec(decBegin);
					double e = Calculators.getDec(decEnd);
					double dist = Math.abs(e - b);
					decDist.setText(distFmtr.format(dist));
				}
			}
			catch (Exception ex) {
				String name = getResources().getString(R.string.appName);
				Log.e(name, "exception", ex);
			}
		}
	}

	private class Compute implements OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				double dist;
				String begin = decBegin.getText().toString().trim();
				String end = decEnd.getText().toString().trim();
				if (begin.length() > 0 && end.length() > 0) {
					double b = Calculators.getDec(decBegin);
					double e = Calculators.getDec(decEnd);
					dist = Math.abs(e - b);
					decDist.setText(distFmtr.format(dist));
				}
				else {
					dist = Calculators.getDec(decDist);
				}
				double vol = Calculators.getDec(decVol);
				double distPerVol = dist / vol;
				numDistPerVol.setText(dpvFmtr.format(distPerVol));
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
			decBegin.getText().clear();
			decEnd.getText().clear();
			decDist.getText().clear();
			decVol.getText().clear();
			numDistPerVol.setText("");
		}
	}
}
