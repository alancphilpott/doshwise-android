package com.example.t00182898.beeradviser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t00182898 on 28/02/2017.
 */
public class BeerExpert {
    List<String> getBrands (String color) {
        List<String> brands = new ArrayList<String>();

        if(color.equals("Amber")){
            brands.add("Jack's Amber Ale");
            brands.add("Red Moose");
        }
        else {
            brands.add("Jailhouse Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}
