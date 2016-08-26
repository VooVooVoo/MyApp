package com.lodz360;

import java.util.List;

/**
 * Created by michalina on 15/08/16.
 */
public interface UserRepository {


    void addUser(User user);

    List<User> getAllUsers();

    User getUserByNameAndPassword(String userName, String userPassword) throws NoSuchUsertException;

}
