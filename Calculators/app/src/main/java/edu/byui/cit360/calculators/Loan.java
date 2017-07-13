package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class Loan extends CalcFragment {
    private EditText decAmt, decAR, intYears, intPPY, intPTD;
    private TextView curPay, curBal;

    public Loan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.loan, container, false);

        decAmt = (EditText)view.findViewById(R.id.decAmt);
        decAR = (EditText)view.findViewById(R.id.decAR);
        intYears = (EditText)view.findViewById(R.id.intYears);
        intPPY = (EditText)view.findViewById(R.id.intPPY);
        curPay = (TextView)view.findViewById(R.id.txtPay);
        intPTD = (EditText)view.findViewById(R.id.intPTD);
        curBal = (TextView)view.findViewById(R.id.txtBal);

        view.findViewById(R.id.btnPay).setOnClickListener(new Payment());
        view.findViewById(R.id.btnBal).setOnClickListener(new Balance());
        view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
        return view;

    }

    private class Payment implements OnClickListener {
        @Override
        public void onClick(View view) {
            try {
                double a = Calculators.getCur(decAmt);
                double ar = Calculators.getDec(decAR) / 100.0;
                int y = Calculators.getInt(intYears);
                int ppy = Calculators.getInt(intPPY);
                double p = computePayment(a, ar, y, ppy);
                curPay.setText(Calculators.curFmtr.format(p));
            }
            catch (Exception ex) {
                String name = getResources().getString(R.string.appName);
                Log.e(name, "exception", ex);
            }
        }
    }

    private class Balance implements OnClickListener {
        @Override
        public void onClick(View view) {
            try {
                double a = Calculators.getCur(decAmt);
                double ar = Calculators.getDec(decAR) / 100.0;
                int y = Calculators.getInt(intYears);
                int ppy = Calculators.getInt(intPPY);
                int ptd = Calculators.getInt(intPTD);
                double b = computeBalance(a, ar, y, ppy, ptd);
                curBal.setText(Calculators.curFmtr.format(b));
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
            decAmt.setText("");
            decAR.setText("");
            intYears.setText("");
            intPPY.setText("");
            curPay.setText("");
            intPTD.setText("");
            curBal.setText("");
        }
    }


    private static double computeBalance(double a, double ar, int y, int ppy, int ptd) {
        double p = computePayment(a, ar, y, ppy);
        double r = ar / ppy;
        double pow = Math.pow(1 + r, ptd);
        double b = a * pow - (p * (pow - 1)) / r;
        b = Math.round(b * 100.0) / 100.0;
        return b;
    }

    private static double computePayment(double a, double ar, int y, int ppy) {
        double r = ar / ppy;
        int n = y * ppy;
        double p = a * r / (1 - Math.pow(1 + r, -n));
        p = Math.round(p * 100.0) / 100.0;
        return p;
    }
}
