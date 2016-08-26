package com.lodz360;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by michalina on 25/08/16.
 */
@Component
public class DatebaseUserRepository implements UserRepository{

    private JdbcTemplate jdbcTemplate;

    public DatebaseUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addUser(User user) {
        try {
            jdbcTemplate.update("INSERT INTO users(name, password, age, weight, height) VALUES (?,?,?,?,?)",
                    user.getName(), user.getPassword(), user.getAge(), user.getWeight(), user.getHeight());
        }
        catch(DuplicateKeyException e) {
            throw  new NoUniqueUserName();
        }

    }
    @Override
    public List<User> getAllUsers() {
return jdbcTemplate.query("Select name, password, age, weight, height From users",
        new UserRowMapper());    }

    @Override
    public User getUserByNameAndPassword(String userName, String userPassword) throws NoSuchUsertException {
        return null;
    }
}
