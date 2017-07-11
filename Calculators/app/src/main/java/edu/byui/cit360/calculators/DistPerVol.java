package edu.byui.cit360.calculators;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class DistPerVol extends Activity {
    private EditText decBegin, decEnd, decDist, decVol;
    private TextView numDistPerVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dist_per_vol);

        decBegin = (EditText)findViewById(R.id.decBegin);
        decEnd = (EditText)findViewById(R.id.decEnd);
        decDist = (EditText)findViewById(R.id.decDist);
        decVol = (EditText)findViewById(R.id.decVol);
        numDistPerVol = (TextView)findViewById(R.id.numDistPerVol);
    }

    public void onDistPerVolClick(View view) {
        try {
            NumberFormat fmtr = LoanCalc.decFmtr;
            double dist;
            if (decDist.getText().toString().trim().length() > 0) {
                decBegin.setText("");
                decEnd.setText("");
                dist = LoanCalc.getDec(decDist);
            }
            else {
                double begin = LoanCalc.getDec(decBegin);
                double end = LoanCalc.getDec(decEnd);
                dist = Math.abs(end - begin);
                fmtr.setMinimumFractionDigits(0);
                fmtr.setMaximumFractionDigits(1);
                decDist.setText(fmtr.format(dist));
            }
            double vol = LoanCalc.getDec(decVol);
            double distPerVol = dist / vol;
            fmtr.setMinimumFractionDigits(1);
            fmtr.setMaximumFractionDigits(1);
            numDistPerVol.setText(LoanCalc.decFmtr.format(distPerVol));
        }
        catch (Exception ex) {
        }
    }

    public void onClearClick(View view) {
        decBegin.setText("");
        decEnd.setText("");
        decDist.setText("");
        decVol.setText("");
        numDistPerVol.setText("");
    }
}
