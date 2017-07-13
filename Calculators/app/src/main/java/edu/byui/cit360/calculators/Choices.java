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
			R.id.btnCompare, R.id.btnTip, R.id.btnShoeSize,
			R.id.btnDistPerVol,
			R.id.btnInvest, R.id.btnLoan,
			R.id.btnOhmsLaw
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
				new ShoeSizeClick(),
				new DistPerVolClick(),
				new InvestClick(),
				new LoanClick(),
				new OhmsLawClick()
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
		Fragment fragment;

		void switchFragment(Fragment fragment) {
			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack so the user can navigate back
			FragmentTransaction trans = getFragmentManager().beginTransaction();
			trans.replace(R.id.frag_cont, fragment);
			trans.addToBackStack(null);

			// Commit the transaction
			trans.commit();
		}
	}

	private class ComparePricesClick extends Click {
		@Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = new ComparePrices();
			}
			switchFragment(fragment);
		}
	}

	private class TipClick extends Click {
		@Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = new Tip();
			}
			switchFragment(fragment);
		}
	}

	private class ShoeSizeClick extends Click {
	   @Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = new ShoeSize();
			}
			switchFragment(fragment);
		}
	}

	private class DistPerVolClick extends Click {
		@Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = new DistPerVol();
			}
			switchFragment(fragment);
		}
	}

	private class InvestClick extends Click {
		@Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = new Invest();
			}
			switchFragment(fragment);
		}
	}

	private class LoanClick extends Click {
		@Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = new Loan();
			}
			switchFragment(fragment);
		}
	}

	private class OhmsLawClick extends Click {
		@Override
		public void onClick(View view) {
			if (fragment == null) {
				fragment = new OhmsLaw();
			}
			switchFragment(fragment);
		}
	}
}
