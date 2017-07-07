package edu.byui.cit360.investcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText numPrinc, numAR, numYears, numPPY;
    private Button btnFV, btnReset;
    private TextView txtFV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        numPrinc = (EditText)findViewById(R.id.numPrinc);
        numAR = (EditText)findViewById(R.id.numAR);
        numYears = (EditText)findViewById(R.id.numYears);
        numPPY = (EditText)findViewById(R.id.numPPY);
        btnFV = (Button)findViewById(R.id.btnFV);
        txtFV = (TextView)findViewById(R.id.txtFV);
        btnReset = (Button)findViewById(R.id.btnReset);
    }

    public void onFVClick(View view) {
        double a = Double.parseDouble(numPrinc.getText().toString());
        double ar = Double.parseDouble(numAR.getText().toString());
        int y = Integer.parseInt(numYears.getText().toString());
        int ppy = Integer.parseInt(numPPY.getText().toString());
        double r = ar / ppy;
        int n = y * ppy;
        double fv = a * Math.pow(1 + r, n);
        fv = Math.round(fv * 100.0) / 100.0;
        txtFV.setText("$" + fv);
    }

    public void onResetClick(View view) {
        numPrinc.setText("");
        numAR.setText("");
        numYears.setText("");
        numPPY.setText("");
        txtFV.setText("");
    }
}
