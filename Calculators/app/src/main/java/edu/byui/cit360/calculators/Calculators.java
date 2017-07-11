package edu.byui.cit360.calculators;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Calculators extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculators);
    }

    public void onCompareClick(View view) {
        Intent intent = new Intent(this, ComparePrices.class);
        startActivity(intent);
    }

    public void onTipClick(View view) {
        Intent intent = new Intent(this, Tip.class);
        startActivity(intent);
    }

    public void onDistPerVolClick(View view) {
        Intent intent = new Intent(this, DistPerVol.class);
        startActivity(intent);
    }

    public void onInvestClick(View view) {
        Intent intent = new Intent(this, InvestCalc.class);
        startActivity(intent);
    }

    public void onLoanClick(View view) {
        Intent intent = new Intent(this, LoanCalc.class);
        startActivity(intent);
    }
}
