package com.lodz360;

import java.util.List;

/**
 * Keeps track of product types.
 */
public interface ProductRepository {

    List<Product> getAllProducts();

    /**
     Searches product by name
     @throws NoSuchProductException
     @return Product with given name. If not found exception of thrown.
     */
    Product getProductByName(String productName) throws NoSuchProductException;
}
