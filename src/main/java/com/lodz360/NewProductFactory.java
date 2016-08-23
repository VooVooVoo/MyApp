package com.lodz360;

import org.springframework.stereotype.Component;

@Component

public class NewProductFactory implements ProductFactory {
    @Override
    public Product create(String name, double protein, double fat, double carbohydrates) {
        return new Product(name, protein, fat, carbohydrates);
    }
}
