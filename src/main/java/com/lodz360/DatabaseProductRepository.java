package com.lodz360;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by michalina on 25/08/16.
 */
@Component
public class DatabaseProductRepository implements ProductRepository {
    private JdbcTemplate jdbcTemplate;

    public DatabaseProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query("Select name, protein, fat, carbohydrates From products",
                new ProductRowMapper());
    }

    @Override
    public Product getProductByName(String productName) throws NoSuchProductException {
        return null;
    }

    @Override
    public void dodaj(Product product) {

         jdbcTemplate.update("INSERT INTO products (name, protein, fat, carbohydrates) VALUES (?, ?, ?, ?)",
                 product.getName(),product.getProtein(), product.getFat(), product.getCarbohydrates());

    }
}
