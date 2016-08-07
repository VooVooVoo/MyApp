package com.lodz360;

/**
 * Created by michalina on 07/08/16.
 */
public class Milk extends Breakfast implements Calculator{

    private double protain = 3.4 / 100;
    private double fat = 3 / 100;

    public Milk(int milk, int eggs, int jogurt) {
        super(milk, eggs, jogurt);
    }

    public double getProtain() {
        return protain;
    }

    public double getFat() {
        return fat;
    }

    public double countProtain() {
        return protain*getMilk();
    }

    public double countFat() {
        return fat*getMilk();
    }
    public String toString2() {
        return   "Protain in your milk: " + countProtain() + "Fat in your milk: " + countFat();
    }
}
