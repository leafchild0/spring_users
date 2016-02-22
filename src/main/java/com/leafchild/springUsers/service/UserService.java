package com.leafchild.springUsers.service;

import com.leafchild.springUsers.entity.UserEntity;

import java.util.List;

/**
 * Created by: leafchild
 * Project: spring_users
 * Date: 2/21/16
 * Time: 16:21
 */
public interface UserService {

    UserEntity getUserByID(long id);

    UserEntity getUserByName(String name);

    void saveUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(UserEntity user);

    List getAllUsers();

    void deleteAllUsers();

    boolean isUserExist(UserEntity user);
}
