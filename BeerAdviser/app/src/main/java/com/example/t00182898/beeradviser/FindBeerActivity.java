package com.example.t00182898.beeradviser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    public void onClickFindBeer(View view) {
        Spinner color = (Spinner) findViewById(R.id.color);
        Object beerTypeAsObj = color.getSelectedItem();
        String beerType = beerTypeAsObj.toString();

        TextView brands = (TextView) findViewById(R.id.brands);
        brands.setText(beerType);
    }
}
