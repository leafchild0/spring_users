package com.leafchild.springUsers.entity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by: leafchild
 * Project: spring_users
 * Date: 2/22/16
 * Time: 18:19
 */
@Repository
@Transactional
public class UserDaoImpl extends AbstractDao implements UserService {


    @Override
    public UserEntity getUserByID(long id) {

        return null;
    }

    @Override
    public UserEntity getUserByName(String name) {


        return null;
    }

    @Override
    public void saveUser(UserEntity user) {
        save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        getSession().update(user);
    }

    @Override
    public void deleteUser(UserEntity user) {

        if(getSession().contains(user)) {
            delete(user);
        } else {
            delete(getSession().merge(user));
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {

        Criteria criteria = getSession().createCriteria(UserEntity.class);
        return (List<UserEntity>) criteria.list();
    }

    @Override
    public void deleteAllUsers() {
        for(UserEntity userEntity : getAllUsers()) {
            delete(userEntity);
        }
    }

    @Override
    public boolean isUserExist(UserEntity user) {


        return getSession().contains(user);
    }
}
