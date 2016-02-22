package com.leafchild.springUsers.service;

import com.leafchild.springUsers.entity.UserDao;
import com.leafchild.springUsers.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by: leafchild
 * Project: spring_users
 * Date: 2/21/16
 * Time: 16:04
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;


    public List getAllUsers() {

        return dao.getAll();
    }


    @Override
    public UserEntity getUserByID(long id) {

        //Find user in DB
        return dao.getById(id);
    }


    @Override
    public UserEntity getUserByName(String name) {

        //Find user in DB by name
        return dao.getByName(name);
    }

    @Override
    public void saveUser(UserEntity user) {

        //Add user in DB
        dao.create(user);

    }

    @Override
    public void updateUser(UserEntity user) {

        //update User in DB
        dao.update(user);

    }

    @Override
    public void deleteUser(UserEntity user) {

        //Remove user from DB
        dao.delete(user);

    }

    @Override
    public void deleteAllUsers() {

        //Remove all Users from DB
        dao.getAll();
    }

    @Override
    public boolean isUserExist(UserEntity user) {
        //Check user in DB

        return dao.getById(user.getId()) != null;
    }

}
