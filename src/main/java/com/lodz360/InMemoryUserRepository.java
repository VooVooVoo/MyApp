package com.lodz360;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michalina on 15/08/16.
 */


public class InMemoryUserRepository implements UserRepository {

   private List<User> usersList = new ArrayList<>();

    public InMemoryUserRepository() {
        usersList.add( new User("admin", "haslo",29, 169,54));
    }

    @Override
    public void addUser(User user) {
        usersList.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersList;
    }

    @Override
    public User getUserByNameAndPassword(String userName,String userPassword) {
        for (User user : getAllUsers()) {
            if (userName.equals(user.getName()) && userPassword.equals(user.getPassword()))
                return user;
        }

        throw new NoSuchUsertException();

    }
}