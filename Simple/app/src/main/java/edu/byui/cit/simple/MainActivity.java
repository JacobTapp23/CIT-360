package edu.byui.cit.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;


public class MainActivity extends Activity {
	private EditText txtOne, txtTwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtOne = findViewById(R.id.editText);
		txtTwo = findViewById(R.id.editText2);

		Button btnCompute = findViewById(R.id.button);
		Button btnClear = findViewById(R.id.button2);
		btnCompute.setOnClickListener(new ComputeHandler());
		btnClear.setOnClickListener(new ClearHandler());
	}


	private final class ComputeHandler implements View.OnClickListener {
		@Override
		public void onClick(View button) {
			NumberFormat formatter = NumberFormat.getInstance();
			String input = txtOne.getText().toString();
			try {
				Number number = formatter.parse(input);
				double one = number.doubleValue();
				double result = one;
				txtTwo.setText(formatter.format(result));
			}
			catch (Exception ex) {
				// Do nothing.
			}
		}
	}


	private final class ClearHandler implements View.OnClickListener {
		@Override
		public void onClick(View button) {
			txtOne.getText().clear();
			txtTwo.getText().clear();
			txtOne.requestFocus();
		}
	}
}
