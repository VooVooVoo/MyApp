package com.lodz360;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by michalina on 25/08/16.
 */
public class ProductRowMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString("name");
        Double protein = resultSet.getDouble("protein");
        Double fat = resultSet.getDouble("fat");
        Double carbohydrates = resultSet.getDouble("carbohydrates");

        Product product = new Product(name,protein,fat,carbohydrates);
        return product;

    }

}
