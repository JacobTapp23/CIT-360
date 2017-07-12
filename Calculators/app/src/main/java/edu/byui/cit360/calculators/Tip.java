package edu.byui.cit360.calculators;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class Tip extends Activity {
    private EditText decCost;
    private TextView num12, num15, num18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip);

        decCost = (EditText)findViewById(R.id.curCost);
        num12 = (TextView)findViewById(R.id.cur12);
        num15 = (TextView)findViewById(R.id.cur15);
        num18 = (TextView)findViewById(R.id.cur18);
    }

    public void onTipClick(View view) {
        try {
            double cost = Loan.getCur(decCost);
            double t12 = cost * 0.12;
            double t15 = cost * 0.15;
            double t18 = cost * 0.18;
            NumberFormat fmtr = Loan.curFmtr;
            num12.setText(fmtr.format(t12));
            num15.setText(fmtr.format(t15));
            num18.setText(fmtr.format(t18));
        }
        catch (Exception ex) {
        }
    }

    private void onClearClick(View view) {
        decCost.setText("");
        num12.setText("");
        num15.setText("");
        num18.setText("");
    }
}
