package com.lodz360;


/**
 * Created by michalina on 10/08/16.
 */
public class Product  {
    private String name;
    private double protain;
    private double fat;
    private double carbohydrates;

    public Product(String name, double protain, double fat, double carbohydrates) {

        this.name = name;
        this.protain = protain / 100;
        this.fat = fat / 100;
        this.carbohydrates = carbohydrates / 100;
    }



    public double getProtain() {
        return this.protain;
    }

    public double getFat() {
        return this.fat;
    }

    public double getCarbohydrates() {
        return this.carbohydrates;
    }

    public String getName() {return name; }


    public String toString() {return getName() + " conteins: " + getProtain() + "grams of protain " + getFat() + "grams of fat "+getCarbohydrates()+"grams of carbohydrates.";}


 /*   @Override
    public boolean equals(Object obj) {
        Product pro = (Product) obj;
        if (pro.name.equals(this.name)) {
            return true;
        }*/
    }




