package edu.byui.cit360.calculators;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class OhmsLaw extends Activity {
    private EditText decVolt, decCur, decResist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ohms_law);

        decVolt = (EditText)findViewById(R.id.decVolt);
        decCur = (EditText)findViewById(R.id.decCur);
        decResist = (EditText)findViewById(R.id.decResist);
    }

    public void onCalcClick(View view) {
        try {
            NumberFormat fmtr = Loan.decFmtr;
            // Todo: allow user to choose number of decimal places.
            String volt = decVolt.getText().toString();
            String cur = decCur.getText().toString();
            String resist = decResist.getText().toString();
            if (cur.length() > 0 && resist.length() > 0) {
                // Compute voltage
                double i = Loan.getDec(decCur);
                double r = Loan.getDec(decResist);
                double v = i * r;
                decVolt.setText(fmtr.format(v));
            }
            else if (volt.length() > 0 && resist.length() > 0) {
                // Compute current
                double v = Loan.getDec(decVolt);
                double r = Loan.getDec(decResist);
                double i = v / r;
                decCur.setText(fmtr.format(i));
            }
            else if (volt.length() > 0 && cur.length() > 0) {
                // Compute resistance
                double v = Loan.getDec(decVolt);
                double i = Loan.getDec(decCur);
                double r = v / i;
                decResist.setText(fmtr.format(r));
            }
        }
        catch (Exception ex) {
        }
    }

    public void onClearClick(View view) {
        decVolt.setText("");
        decCur.setText("");
        decResist.setText("");
    }
}
