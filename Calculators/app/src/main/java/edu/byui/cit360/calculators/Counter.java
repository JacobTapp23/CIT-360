package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

public class Counter extends CalcFragment {
	private TextView intCount;
	private int count;
	private NumberFormat intFmtr;

	public Counter() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intFmtr = NumberFormat.getIntegerInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment.
		View view = inflater.inflate(R.layout.counter, container, false);

		intCount = (TextView)view.findViewById(R.id.cntIntCount);
		intCount.setText(intFmtr.format(count));

		view.findViewById(R.id.cntBtnPlusOne).setOnClickListener(new Add(1));
		view.findViewById(R.id.cntBtnPlusTwo).setOnClickListener(new Add(2));
		view.findViewById(R.id.cntBtnPlusThree).setOnClickListener(new Add(3));
		view.findViewById(R.id.cntBtnPlusFive).setOnClickListener(new Add(5));
		view.findViewById(R.id.cntBtnPlusTen).setOnClickListener(new Add(10));

		view.findViewById(R.id.cntBtnClear).setOnClickListener(new Clear());
		return view;
	}

	private class Add implements OnClickListener {
		private final int quant;

		public Add(int quant) {
			this.quant = quant;
		}

		@Override
		public void onClick(View view) {
			count += quant;
			intCount.setText(intFmtr.format(count));
		}
	}

	private class Clear implements OnClickListener {
		@Override
		public void onClick(View view) {
			count = 0;
			intCount.setText(intFmtr.format(count));
		}
	}
}
