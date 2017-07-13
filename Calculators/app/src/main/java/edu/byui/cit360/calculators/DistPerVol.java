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

public class DistPerVol extends CalcFragment {
    private EditText decBegin, decEnd, decDist, decVol;
    private TextView numDistPerVol;

    public DistPerVol() {
        // Required empty public constructor
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
        view.findViewById(R.id.btnCompute).setOnClickListener(new Compute());
        view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
        return view;
    }

    private class Compute implements OnClickListener {
        @Override
        public void onClick(View view) {
            try {
                NumberFormat fmtr = Calculators.decFmtr;
                double dist;
                if (decDist.getText().toString().trim().length() > 0) {
                    decBegin.setText("");
                    decEnd.setText("");
                    dist = Calculators.getDec(decDist);
                }
                else {
                    double begin = Calculators.getDec(decBegin);
                    double end = Calculators.getDec(decEnd);
                    dist = Math.abs(end - begin);
                    fmtr.setMinimumFractionDigits(0);
                    fmtr.setMaximumFractionDigits(1);
                    decDist.setText(fmtr.format(dist));
                }
                double vol = Calculators.getDec(decVol);
                double distPerVol = dist / vol;
                fmtr.setMinimumFractionDigits(1);
                fmtr.setMaximumFractionDigits(1);
                numDistPerVol.setText(Calculators.decFmtr.format(distPerVol));
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
            decBegin.setText("");
            decEnd.setText("");
            decDist.setText("");
            decVol.setText("");
            numDistPerVol.setText("");
        }
    }
}
