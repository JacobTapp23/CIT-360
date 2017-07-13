package edu.byui.cit360.calculators;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Choices extends CalcFragment {
    public Choices() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.choices, container, false);

        // Add click listeners to all the buttons.
        view.findViewById(R.id.btnCompare).setOnClickListener(new ComparePricesClick());
        view.findViewById(R.id.btnTip).setOnClickListener(new TipClick());
        view.findViewById(R.id.btnShoeSize).setOnClickListener(new ShoeSizeClick());
        view.findViewById(R.id.btnDistPerVol).setOnClickListener(new DistPerVolClick());
        view.findViewById(R.id.btnInvest).setOnClickListener(new InvestClick());
        view.findViewById(R.id.btnLoan).setOnClickListener(new LoanClick());
        view.findViewById(R.id.btnOhmsLaw).setOnClickListener(new OhmsLawClick());

        return view;
    }

    private void switchFragment(Fragment fragment) {
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.frag_cont, fragment);
        trans.addToBackStack(null);

        // Commit the transaction
        trans.commit();
    }

    private class ComparePricesClick implements OnClickListener {
        private Fragment fragment;

        @Override
        public void onClick(View view) {
            if (fragment == null) {
                fragment = new ComparePrices();
            }
            switchFragment(fragment);
        }
    }

    private class TipClick implements OnClickListener {
        private Fragment fragment;

        @Override
        public void onClick(View view) {
            if (fragment == null) {
                fragment = new Tip();
            }
            switchFragment(fragment);
        }
    }

    private class ShoeSizeClick implements OnClickListener {
        private Fragment fragment;

       @Override
        public void onClick(View view) {
            if (fragment == null) {
                fragment = new ShoeSize();
            }
            switchFragment(fragment);
        }
    }

    private class DistPerVolClick implements OnClickListener {
        private Fragment fragment;

        @Override
        public void onClick(View view) {
            if (fragment == null) {
                fragment = new DistPerVol();
            }
            switchFragment(fragment);
        }
    }

    private class InvestClick implements OnClickListener {
        private Fragment fragment;

        @Override
        public void onClick(View view) {
            if (fragment == null) {
                fragment = new Invest();
            }
            switchFragment(fragment);
        }
    }

    private class LoanClick implements OnClickListener {
        private Fragment fragment;

        @Override
        public void onClick(View view) {
            if (fragment == null) {
                fragment = new Loan();
            }
            switchFragment(fragment);
        }
    }

    private class OhmsLawClick implements OnClickListener {
        private Fragment fragment;

        @Override
        public void onClick(View view) {
            if (fragment == null) {
                fragment = new OhmsLaw();
            }
            switchFragment(fragment);
        }
    }
}
