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
	private EditText curCost;
	private TextView cur12, cur15, cur18;
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

		curCost = (EditText)view.findViewById(R.id.tipCurCost);
		cur12 = (TextView)view.findViewById(R.id.tipCur12);
		cur15 = (TextView)view.findViewById(R.id.tipCur15);
		cur18 = (TextView)view.findViewById(R.id.tipCur18);
		view.findViewById(R.id.tipBtnCompute).setOnClickListener(new Compute());
		view.findViewById(R.id.tipBtnClear).setOnClickListener(new Clear());
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		curCost.requestFocus();
	}

	private class Compute implements OnClickListener {
		@Override
		public void onClick(View view) {
			try {
				double cost = Calculators.getCur(curCost);
				double t12 = cost * 0.12;
				double t15 = cost * 0.15;
				double t18 = cost * 0.18;
				cur12.setText(curFmtr.format(t12));
				cur15.setText(curFmtr.format(t15));
				cur18.setText(curFmtr.format(t18));
			}
			catch (Exception ex) {
				Log.e(Calculators.TAG, "exception", ex);
			}
		}
	}

	private class Clear implements OnClickListener {
		@Override
		public void onClick(View view) {
			curCost.getText().clear();
			cur12.setText("");
			cur15.setText("");
			cur18.setText("");
		}
	}
}
