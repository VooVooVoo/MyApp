package com.lodz360;


/**
 * Created by michalina on 10/08/16.
 */
public class Product  {
    private String name;
    private double protein;
    private double fat;
    private double carbohydrates;

    public Product(String name, double protein, double fat, double carbohydrates) {

        this.name = name;
        this.protein = protein / 100;
        this.fat = fat / 100;
        this.carbohydrates = carbohydrates / 100;
    }



    public double getProtein() {
        return this.protein;
    }

    public double getFat() {
        return this.fat;
    }

    public double getCarbohydrates() {
        return this.carbohydrates;
    }

    public String getName() {return name; }


    public String toString() {return getName() + " contain: " + getProtein() + "grams of protein " + getFat() + "grams of fat "+getCarbohydrates()+"grams of carbohydrates.";}


 /*   @Override
    public boolean equals(Object obj) {
        Product pro = (Product) obj;
        if (pro.name.equals(this.name)) {
            return true;
        }*/
    }




