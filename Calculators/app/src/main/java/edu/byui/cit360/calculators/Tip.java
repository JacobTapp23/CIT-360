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

public class Tip extends CalcFragment {
	private EditText decCost;
	private TextView num12, num15, num18;
	private NumberFormat curFmtr;

	public Tip() {
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
		View view = inflater.inflate(R.layout.tip, container, false);

		decCost = (EditText)view.findViewById(R.id.curCost);
		num12 = (TextView)view.findViewById(R.id.cur12);
		num15 = (TextView)view.findViewById(R.id.cur15);
		num18 = (TextView)view.findViewById(R.id.cur18);
		view.findViewById(R.id.btnCompute).setOnClickListener(new Compute());
		view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
		return view;
	}

	private class Compute implements OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				double cost = Calculators.getCur(decCost);
				double t12 = cost * 0.12;
				double t15 = cost * 0.15;
				double t18 = cost * 0.18;
				num12.setText(curFmtr.format(t12));
				num15.setText(curFmtr.format(t15));
				num18.setText(curFmtr.format(t18));
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
			decCost.getText().clear();
			num12.setText("");
			num15.setText("");
			num18.setText("");
		}
	}
}
