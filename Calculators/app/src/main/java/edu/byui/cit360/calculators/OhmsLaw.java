package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.NumberFormat;

public class OhmsLaw extends CalcFragment {
    private EditText decVolt, decCur, decResist;

    public OhmsLaw() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ohms_law, container, false);

        decVolt = (EditText)view.findViewById(R.id.decVolt);
        decCur = (EditText)view.findViewById(R.id.decCur);
        decResist = (EditText)view.findViewById(R.id.decResist);
        view.findViewById(R.id.btnCompute).setOnClickListener(new Compute());
        view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
        return view;
    }

    private class Compute implements OnClickListener {
        @Override
        public void onClick(View view) {
            try {
                NumberFormat fmtr = Calculators.decFmtr;
                // Todo: allow user to choose number of decimal places.
                String volt = decVolt.getText().toString();
                String cur = decCur.getText().toString();
                String resist = decResist.getText().toString();
                if (cur.length() > 0 && resist.length() > 0) {
                    // Compute voltage
                    double i = Calculators.getDec(decCur);
                    double r = Calculators.getDec(decResist);
                    double v = i * r;
                    decVolt.setText(fmtr.format(v));
                }
                else if (volt.length() > 0 && resist.length() > 0) {
                    // Compute current
                    double v = Calculators.getDec(decVolt);
                    double r = Calculators.getDec(decResist);
                    double i = v / r;
                    decCur.setText(fmtr.format(i));
                }
                else if (volt.length() > 0 && cur.length() > 0) {
                    // Compute resistance
                    double v = Calculators.getDec(decVolt);
                    double i = Calculators.getDec(decCur);
                    double r = v / i;
                    decResist.setText(fmtr.format(r));
                }
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
            decVolt.setText("");
            decCur.setText("");
            decResist.setText("");
        }
    }
}
