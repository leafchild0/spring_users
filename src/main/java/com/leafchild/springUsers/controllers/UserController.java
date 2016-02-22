package com.leafchild.springUsers.controllers;

import com.leafchild.springUsers.entity.UserDao;
import com.leafchild.springUsers.entity.UserEntity;
import com.leafchild.springUsers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by: leafchild
 * Project: spring_users
 * Date: 2/21/16
 * Time: 15:22
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService uService;


    //Get All the users
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> showUsers() {

        List<UserEntity> allUsers = uService.getAllUsers();
        if(allUsers.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> findUserByID(@PathVariable(value = "id") long id) {
        //Check ID
        //Check user
        UserEntity user = uService.getUserByID(id);

        if(user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {

        //Check required params
        //Create user
        uService.saveUser(user);
        //Check on errors if need
        //Return results
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") long id, @RequestBody UserEntity user) {

        UserEntity foundUser = uService.getUserByID(id);

        if(foundUser == null) {
            System.out.println("Requested user " + id + " not found");
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }

        foundUser.setName(user.getName());
        foundUser.setPhone(user.getPhone());

        //Update user
        uService.updateUser(user);
        //Check on errors
        //Return results
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {

        UserEntity foundUser = uService.getUserByID(id);

        if(foundUser == null) {
            System.out.println("Requested user " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Delete user
        uService.deleteUser(foundUser);
        //Check on errors
        //Return results
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllUsers() {

        //Delete all user
        uService.deleteAllUsers();
        //Return results
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
