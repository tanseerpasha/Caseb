package com.example.tanseer.deliciousfoodies.mainview;

import android.support.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supreme on 8/18/2017.
 */

@Keep
public class Cuisine {
    private String cuisinename;
    private String cuisinemore;
    private ArrayList<DishList> dishes;


    public Cuisine() {
        // Needed for Firebase
    }

    public Cuisine(String cuisinename, String cuisinemore,ArrayList<DishList> dishes) {
        this.cuisinename = cuisinename;
        this.cuisinemore = cuisinemore;
        this.dishes = dishes;

    }

    public String getCuisinemore() {
        return cuisinemore;
    }

    public String getCuisinename() {
        return cuisinename;
    }

    public void setCuisinemore(String cuisinemore) {
        this.cuisinemore = cuisinemore;
    }

    public void setCuisinename(String cuisinename) {
        this.cuisinename = cuisinename;
    }

    public List getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<DishList> dishes) {
        this.dishes = dishes;
    }
}
