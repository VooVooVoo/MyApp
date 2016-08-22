package com.lodz360;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by michalina on 11/08/16.
 */
@Component
public class InMemoryProductRepository implements ProductRepository {


    List<Product> productList = new ArrayList<>();
    public InMemoryProductRepository(){
        productList.add(new Product("Milk", 3.4, 3, 0));
        productList.add(new Product("Egg", 13, 11, 0));
        productList.add(new Product("Cereal", 8, 0.4, 84));
        productList.add(new Product("Butter", 0.9, 89, 0.1));
        productList.add(new Product("Baton", 10, 0.8, 0.1));

    }


    @Override
    public List<Product> getAllProducts() {

        return productList;
    }



    @Override
    public Product getProductByName(String productName)  {
        List<Product> allProducts = getAllProducts();
        for (Product product: allProducts){
            if(product.getName().equals(productName))
                return product;
        }
        throw new NoSuchProductException();
    }

    @Override
    public List<Product> dodaj(Product product) {
        productList.add(product);
        return null;
    }
}
