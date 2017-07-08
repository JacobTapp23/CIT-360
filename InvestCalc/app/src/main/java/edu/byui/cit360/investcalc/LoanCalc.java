package edu.byui.cit360.investcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoanCalc extends Activity {
    private EditText decAmt, decAR, intYears, intPPY, intPTD;
    private TextView txtPay, txtBal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_calc);

        decAmt = (EditText)findViewById(R.id.decAmt);
        decAR = (EditText)findViewById(R.id.decAR);
        intYears = (EditText)findViewById(R.id.intYears);
        intPPY = (EditText)findViewById(R.id.intPPY);
        txtPay = (TextView)findViewById(R.id.txtPay);
        intPTD = (EditText)findViewById(R.id.intPTD);
        txtBal = (TextView)findViewById(R.id.txtBal);
    }

    public void onPayClick(View view) {
        double a = getDec(decAmt);
        double ar = getDec(decAR) / 100.0;
        int y = getInt(intYears);
        int ppy = getInt(intPPY);
        double p = computePayment(a, ar, y, ppy);
        txtPay.setText("$" + p);
    }

    public void onBalClick(View view) {
        double a = getDec(decAmt);
        double ar = getDec(decAR) / 100.0;
        int y = getInt(intYears);
        int ppy = getInt(intPPY);
        int ptd = getInt(intPTD);
        double b = computeBalance(a, ar, y, ppy, ptd);
        txtBal.setText("$" + b);
    }

    public void onResetClick(View view) {
        decAmt.setText("");
        decAR.setText("");
        intYears.setText("");
        intPPY.setText("");
        txtPay.setText("");
        intPTD.setText("");
        txtBal.setText("");
    }

    public void onFVClick(View view) {
        Intent intent = new Intent(this, InvestCalc.class);
        startActivity(intent);
    }


    private double computePayment(double a, double ar, int y, int ppy) {
        double r = ar / ppy;
        int n = y * ppy;
        double p = a * r / (1 - Math.pow(1 + r, -n));
        p = Math.round(p * 100.0) / 100.0;
        return p;
    }

    private double computeBalance(double a, double ar, int y, int ppy, int ptd) {
        double p = computePayment(a, ar, y, ppy);
        double r = ar / ppy;
        double pow = Math.pow(1 + r, ptd);
        double b = a * pow - (p * (pow - 1)) / r;
        b = Math.round(b * 100.0) / 100.0;
        return b;
    }


    static int getInt(EditText text) {
        return Integer.parseInt(text.getText().toString());
    }

    static double getDec(EditText text) {
        return Double.parseDouble(text.getText().toString());
    }}
