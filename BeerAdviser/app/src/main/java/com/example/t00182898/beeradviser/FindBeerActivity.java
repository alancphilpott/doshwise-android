package com.example.t00182898.beeradviser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class FindBeerActivity extends AppCompatActivity {

    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);

        Spinner color = (Spinner) findViewById(R.id.color);
        color.setSelection(-1);
    }

    public void onClickFindBeer(View view) {
        Spinner color = (Spinner) findViewById(R.id.color);
        Object beerTypeAsObj = color.getSelectedItem();
        String beerType = beerTypeAsObj.toString();

        List<String> brandsList = expert.getBrands(beerType);
        StringBuilder brandsFormatted = new StringBuilder();
        for(String brand : brandsList) {
            brandsFormatted.append(brand).append("\n");
        }

        TextView brands = (TextView) findViewById(R.id.brands);
        brands.setText(brandsFormatted);
    }
}