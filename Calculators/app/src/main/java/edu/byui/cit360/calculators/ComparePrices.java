package edu.byui.cit360.calculators;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ComparePrices extends Activity {
    private static class Compare {
        EditText curPrice, decQuant;
        TextView curPer;
    }

    private Compare[] compares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_prices);

        compares = new Compare[3];
        for (int i = 0;  i < compares.length;  ++i) {
            compares[i] = new Compare();
        }
        Compare comp = compares[0];
        comp.curPrice = (EditText)findViewById(R.id.curPrice1);
        comp.decQuant = (EditText)findViewById(R.id.decQuant1);
        comp.curPer = (TextView)findViewById(R.id.curPer1);
        comp = compares[1];
        comp.curPrice = (EditText)findViewById(R.id.curPrice2);
        comp.decQuant = (EditText)findViewById(R.id.decQuant2);
        comp.curPer = (TextView)findViewById(R.id.curPer2);
        comp = compares[2];
        comp.curPrice = (EditText)findViewById(R.id.curPrice3);
        comp.decQuant = (EditText)findViewById(R.id.decQuant3);
        comp.curPer = (TextView)findViewById(R.id.curPer3);
    }

    public void onCompareClick(View view) {
        clearResults(view);

        double min = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0;  i < compares.length;  ++i) {
            try {
                Compare comp = compares[i];
                double price = LoanCalc.getCur(comp.curPrice);
                double quant = LoanCalc.getDec(comp.decQuant);
                double per = price / quant;
                comp.curPer.setText(LoanCalc.curFmtr.format(per));
                if (per < min) {
                    min = per;
                    index = i;
                }
            }
            catch (Exception ex) {
            }
        }

        if (index != -1) {
            Compare comp = compares[index];
            comp.curPer.setBackgroundColor(getResources().getColor(R.color.colorBest, null));
        }
    }

    public void onClearClick(View view) {
        for (int i = 0;  i < compares.length;  ++i) {
            Compare comp = compares[i];
            comp.curPrice.setText("");
            comp.decQuant.setText("");
        }
        clearResults(view);
    }

    private void clearResults(View view) {
        for (int i = 0;  i < compares.length;  ++i) {
            Compare comp = compares[i];
            comp.curPer.setText("");
            comp.curPer.setBackground(null);
        }
    }
}
