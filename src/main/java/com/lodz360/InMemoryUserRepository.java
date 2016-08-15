package com.lodz360;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michalina on 15/08/16.
 */
@Component
public class InMemoryUserRepository implements UserRepository {

    List<User> usersList = new ArrayList<>();

    public User getUser() {
        User user = new User("",0,0,0);

                @RequestParam(value = "name") String name,
                @RequestParam(value = "age") Integer age,
                @RequestParam(value = "weight") Integer weight,
                @RequestParam(value = "height") Integer height, Model model) {


            User user = new User(name, age, weight, height);
            usersList.add(user);

            return user;
        }

    }

        @Override
    public List<User> getAllUsers() {
        return null;
    }
}
