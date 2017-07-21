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
	public static final String TAG = "BYU-I Calcs";
	public static int backgrndColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculators);

		if (savedInstanceState == null) {
			// Get the background color so that it can be used by the fragments.
			TypedArray array = getTheme().obtainStyledAttributes(
					new int[]{android.R.attr.windowBackground});
			backgrndColor = array.getColor(0, 0xff00ff);
			array.recycle();

			// Add Choices as the first fragment.
			Fragment fragment = new Choices();
			FragmentTransaction trans = getFragmentManager().beginTransaction();
			trans.add(R.id.frag_cont, fragment);
			trans.commit();
		}
	}


	private static NumberFormat intFmtr = NumberFormat.getIntegerInstance();
	private static NumberFormat curFmtr = NumberFormat.getCurrencyInstance();
	private static NumberFormat perFmtr = NumberFormat.getPercentInstance();
	private static NumberFormat decFmtr = NumberFormat.getInstance();

	public static int getInt(EditText text) throws ParseException {
		return getInt(text.getText().toString());
	}

	public static double getCur(EditText text) throws ParseException {
		return getCur(text.getText().toString());
	}

	public static double getPerc(EditText text) throws ParseException {
		return getPerc(text.getText().toString());
	}

	public static double getDec(EditText text) throws ParseException {
		return getDec(text.getText().toString());
	}

	public static int getInt(String s) throws ParseException {
		Number val;
		try {
			val = intFmtr.parse(s);
		}
		catch (Exception ex) {
			val = Double.parseDouble(s);
		}
		return val.intValue();
	}

	public static double getCur(String s) throws ParseException {
		Number val;
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

	public static double getPerc(String s) throws ParseException {
		Number val;
		try {
			val = perFmtr.parse(s);
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

	public static double getDec(String s) throws ParseException {
		Number val;
		try {
			val = decFmtr.parse(s);
		}
		catch (Exception ex) {
			val = Double.parseDouble(s);
		}
		return val.doubleValue();
	}


//	public static String camelFromPhrase(String phrase) {
//		int len = phrase.length();
//		StringBuilder camel = new StringBuilder(len);
//		boolean end = false;
//		for (int i = 0;  i < len;  ++i) {
//			char c = phrase.charAt(i);
//			if (Character.isWhitespace(c)) {
//				end = true;
//			}
//			else {
//				if (end) {
//					c = Character.toUpperCase(c);
//				}
//				camel.append(c);
//				end = false;
//			}
//		}
//		return camel.toString();
//	}
}
