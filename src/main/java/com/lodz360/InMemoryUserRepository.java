package com.lodz360;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michalina on 15/08/16.
 */
@Component
public class InMemoryUserRepository implements UserRepository {

    List<User> usersList = new ArrayList<>();

    @Override
    public void addUser(User user) {
        usersList.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersList;
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }


}