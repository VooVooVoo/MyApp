package com.lodz360;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by michalina on 25/08/16.
 */
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException{
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        int height = resultSet.getInt("height");
        int weight = resultSet.getInt("weight");
        int age = resultSet.getInt("age");
        User user = new User(name,password, age, height, weight);
        return user;

    }


}
