package edu.byui.cit360.calculators;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InvestCalc extends Activity {
    private EditText numPrinc, numAR, numYears, numPPY;
    private TextView curFV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invest_calc);

        numPrinc = (EditText)findViewById(R.id.numPrinc);
        numAR = (EditText)findViewById(R.id.numAR);
        numYears = (EditText)findViewById(R.id.numYears);
        numPPY = (EditText)findViewById(R.id.numPPY);
        curFV = (TextView)findViewById(R.id.txtFV);
    }

    public void onFVClick(View view) {
        try {
            double a = LoanCalc.getCur(numPrinc);
            double ar = LoanCalc.getDec(numAR) / 100.0;
            int y = LoanCalc.getInt(numYears);
            int ppy = LoanCalc.getInt(numPPY);
            double r = ar / ppy;
            int n = y * ppy;
            double fv = a * Math.pow(1 + r, n);
            fv = Math.round(fv * 100.0) / 100.0;
            curFV.setText(LoanCalc.curFmtr.format(fv));
        }
        catch (Exception ex) {
        }
    }

    public void onResetClick(View view) {
        numPrinc.setText("");
        numAR.setText("");
        numYears.setText("");
        numPPY.setText("");
        curFV.setText("");
    }
}
