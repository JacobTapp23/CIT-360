package edu.byui.cit360.calculators;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Invest extends Activity {
    private EditText numPrinc, numAR, numYears, numPPY;
    private TextView curFV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invest);

        numPrinc = (EditText)findViewById(R.id.numPrinc);
        numAR = (EditText)findViewById(R.id.numAR);
        numYears = (EditText)findViewById(R.id.numYears);
        numPPY = (EditText)findViewById(R.id.numPPY);
        curFV = (TextView)findViewById(R.id.txtFV);
    }

    public void onFVClick(View view) {
        try {
            double a = Loan.getCur(numPrinc);
            double ar = Loan.getDec(numAR) / 100.0;
            int y = Loan.getInt(numYears);
            int ppy = Loan.getInt(numPPY);
            double r = ar / ppy;
            int n = y * ppy;
            double fv = a * Math.pow(1 + r, n);
            fv = Math.round(fv * 100.0) / 100.0;
            curFV.setText(Loan.curFmtr.format(fv));
        }
        catch (Exception ex) {
        }
    }

    public void onClearClick(View view) {
        numPrinc.setText("");
        numAR.setText("");
        numYears.setText("");
        numPPY.setText("");
        curFV.setText("");
    }
}
