package com.lodz360;

public interface ProductFactory {
    Product create(String name, double protein, double fat, double carbohydrates);
}
