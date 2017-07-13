package edu.byui.cit360.calculators;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ComparePrices extends CalcFragment {
    private static class Product {
        EditText curPrice, decQuant;
        TextView curPer;
    }

    private Product[] products;

    public ComparePrices() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.compare_prices, container, false);

        products = new Product[3];
        for (int i = 0; i < products.length; ++i) {
            products[i] = new Product();
        }
        Product prod = products[0];
        prod.curPrice = (EditText)view.findViewById(R.id.curPrice1);
        prod.decQuant = (EditText)view.findViewById(R.id.decQuant1);
        prod.curPer = (TextView)view.findViewById(R.id.curPer1);
        prod = products[1];
        prod.curPrice = (EditText)view.findViewById(R.id.curPrice2);
        prod.decQuant = (EditText)view.findViewById(R.id.decQuant2);
        prod.curPer = (TextView)view.findViewById(R.id.curPer2);
        prod = products[2];
        prod.curPrice = (EditText)view.findViewById(R.id.curPrice3);
        prod.decQuant = (EditText)view.findViewById(R.id.decQuant3);
        prod.curPer = (TextView)view.findViewById(R.id.curPer3);
        view.findViewById(R.id.btnCompute).setOnClickListener(new Compare());
        view.findViewById(R.id.btnClear).setOnClickListener(new Clear());
        return view;
    }

    private class Compare implements OnClickListener {
        @Override
        public void onClick(View view) {
            clearResults(view);

            double min = Double.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < products.length; ++i) {
                try {
                    Product prod = products[i];
                    double price = Calculators.getCur(prod.curPrice);
                    double quant = Calculators.getDec(prod.decQuant);
                    double per = price / quant;
                    prod.curPer.setText(Calculators.curFmtr.format(per));
                    if (per < min) {
                        min = per;
                        index = i;
                    }
                }
                catch (Exception ex) {
                    String name = getResources().getString(R.string.appName);
                    Log.e(name, "exception", ex);
                }
            }

            if (index != -1) {
                Product prod = products[index];
                prod.curPer.setBackgroundColor(getResources().getColor(R.color.colorBest, null));
            }
        }
    }

    private class Clear implements OnClickListener {
        @Override
        public void onClick(View view) {
            for (Product prod : products) {
                prod.curPrice.setText("");
                prod.decQuant.setText("");
            }
            clearResults(view);
        }
    }

    private void clearResults(View view) {
        for (Product prod : products) {
            prod.curPer.setText("");
            prod.curPer.setBackground(null);
        }
    }
}
