package com.lodz360;

public class NutritionalValue {

    private final double proteinsInGrams;
    private final double carbsInGrams;
    private final double fatInGrams;

    public final static NutritionalValue ZERO = new NutritionalValue();

    public NutritionalValue(double proteinsInGrams, double fatInGrams, double carbsInGrams) {
        this.proteinsInGrams = proteinsInGrams;
        this.fatInGrams = fatInGrams;
        this.carbsInGrams = carbsInGrams;
    }

    public NutritionalValue() {
        this(0,0,0);
    }

    public NutritionalValue addMeal(Product product, double amountInGrams) {
        double newProteinsInGrams = proteinsInGrams + product.getProtein() * amountInGrams;
        double newFatInGrams = fatInGrams + product.getFat() * amountInGrams;
        double newCarbsInGrams = carbsInGrams + product.getCarbohydrates() * amountInGrams;
        return new NutritionalValue(newProteinsInGrams, newFatInGrams, newCarbsInGrams);
    }

    public double getProteinsInGrams() {
        return proteinsInGrams;
    }

    public double getFatInGrams() {
        return fatInGrams;
    }

    public double getCarbsInGrams() {
        return carbsInGrams;
    }
}
