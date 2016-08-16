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
    public List<User> addUser(User user) {
        List<User> allUsers = usersList;
        allUsers.add(user);
        return allUsers;

    }

}