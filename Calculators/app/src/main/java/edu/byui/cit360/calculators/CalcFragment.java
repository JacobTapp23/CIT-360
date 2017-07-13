package edu.byui.cit360.calculators;

import android.app.Fragment;
import android.os.Bundle;

public class CalcFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setBackgroundColor(Calculators.backgrndColor);
    }
}
