package edu.byui.cit360.calculators;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Choices extends CalcFragment {
	private final int[] buttonIDs = {
			R.id.chcBtnCompPrices, R.id.chcBtnTip,
			R.id.chcBtnDistPerVol,
			R.id.chcBtnInvest, R.id.chcBtnLoan,
			R.id.chcBtnOhmsLaw,
			R.id.chcBtnCount
	};
	private Click[] clickListeners;

	public Choices() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		clickListeners = new Click[] {
				new ComparePricesClick(),
				new TipClick(),
				new DistPerVolClick(),
				new InvestClick(),
				new LoanClick(),
				new OhmsLawClick(),
				new CounterClick()
		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment.
		View view = inflater.inflate(R.layout.choices, container, false);

		// Add click listeners to all the buttons.
		for (int i = 0;  i < buttonIDs.length;  ++i) {
			view.findViewById(buttonIDs[i]).setOnClickListener(clickListeners[i]);
		}

		return view;
	}


	private abstract class Click implements OnClickListener {
		private Fragment fragment;

		@Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = create();
			}

			// Replace whatever is in the fragment_container view with
			// this fragment, and add the transaction to the back stack
			// so the user can navigate back
			FragmentTransaction trans = getFragmentManager().beginTransaction();
			trans.replace(R.id.frag_cont, fragment);
			trans.addToBackStack(null);

			// Commit the transaction
			trans.commit();
		}

		public abstract Fragment create();
	}

	private class ComparePricesClick extends Click {
		@Override
		public Fragment create() {
			return new ComparePrices();
		}
	}

	private class TipClick extends Click {
		@Override
		public Fragment create() {
			return new Tip();
		}
	}

	private class DistPerVolClick extends Click {
		@Override
		public Fragment create() {
			return new DistPerVol();
		}
	}

	private class InvestClick extends Click {
		@Override
		public Fragment create() {
			return new Invest();
		}
	}

	private class LoanClick extends Click {
		@Override
		public Fragment create() {
			return new Loan();
		}
	}

	private class OhmsLawClick extends Click {
		@Override
		public Fragment create() {
			return new OhmsLaw();
		}
	}

	private class CounterClick extends Click {
		@Override
		public Fragment create() {
			return new Counter();
		}
	}
}
