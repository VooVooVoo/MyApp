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


    double countProtein = protain*getMilk();

    double countFat = fat*getMilk();


}
