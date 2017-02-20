package phil.alan.coffeecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnMinus(View view) {
        int q; TextView qty = (TextView) findViewById(R.id.txtQty);
        q = Integer.parseInt(qty.getText().toString()); q--;

        if(q <= 0){
            qty.setText("0");
        }
        else {
            qty.setText(String.valueOf(q));
        }
    }

    public void btnPlus(View view) {
        int q; TextView qty = (TextView) findViewById(R.id.txtQty);
        q = Integer.parseInt(qty.getText().toString()); q++;

        if(q <= 0){
            qty.setText("0");
        }
        else {
            qty.setText(String.valueOf(q));
        }
    }

    public void btnOrder(View view) {
        int q; TextView qty = (TextView) findViewById(R.id.txtQty);
        TextView orderTotal = (TextView) findViewById(R.id.totalCost);
        EditText cost = (EditText) findViewById(R.id.numPicker);

        int userCost = Integer.parseInt(cost.getText().toString());

        q = Integer.parseInt(qty.getText().toString());
        double total = userCost*q;
        if(total <= 0) {
            orderTotal.setText("Total: $0");
        }
        else {
            String totalAsString = "Total: $" + String.valueOf(total);
            orderTotal.setText(totalAsString);
        }
    }
}
