package edu.byui.cit360.calculators;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.ParseException;

public class Calculators extends Activity {
    public static NumberFormat decFmtr = NumberFormat.getInstance();
    public static NumberFormat curFmtr = NumberFormat.getCurrencyInstance();
    public static NumberFormat intFmtr = NumberFormat.getIntegerInstance();

    public static int getInt(EditText text) throws ParseException {
        Number val = null;
        String s = text.getText().toString();
        try {
            val = intFmtr.parse(s);
        }
        catch (Exception ex) {
            val = Double.parseDouble(s);
        }
        return val.intValue();
    }

    public static double getDec(EditText text) throws ParseException {
        Number val = null;
        String s = text.getText().toString();
        try {
            val = decFmtr.parse(s);
        }
        catch (Exception ex) {
            val = Double.parseDouble(s);
        }
        return val.doubleValue();
    }

    public static double getCur(EditText text) throws ParseException {
        Number val = null;
        String s = text.getText().toString();
        try {
            val = curFmtr.parse(s);
        }
        catch (Exception ex) {
            try {
                val = decFmtr.parse(s);
            }
            catch (Exception ex2) {
                val = Double.parseDouble(s);
            }
        }
        return val.doubleValue();
    }

    public static int backgrndColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculators);

        // Get the background color so that
        // it can be used by the fragments.
        TypedArray array = getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.windowBackground });
        backgrndColor = array.getColor(0, 0xff00ff);
        array.recycle();

        // Add Choices as the first fragment
        Fragment fragment = new Choices();
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.add(R.id.frag_cont, fragment);
        trans.commit();
    }
}
