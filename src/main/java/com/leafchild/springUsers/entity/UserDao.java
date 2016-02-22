package com.leafchild.springUsers.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class UserDao {

    // Private fields

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the user in the database.
     */
    public void create(UserEntity user) {
        entityManager.persist(user);
    }

    /**
     * Delete the user from the database.
     */
    public void delete(UserEntity user) {
        if (entityManager.contains(user))
            entityManager.remove(user);
        else
            entityManager.remove(entityManager.merge(user));
    }

    /**
     * Return all the users stored in the database.
     */
    @SuppressWarnings("unchecked")
    public List getAll() {
        return entityManager.createQuery("from users").getResultList();
    }

    /**
     * Return the user having the passed email.
     */
    public UserEntity getByName(String name) {
        return (UserEntity) entityManager.createQuery(
                "from users where name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    /**
     * Return the user having the passed id.
     */
    public UserEntity getById(long id) {
        return entityManager.find(UserEntity.class, id);
    }

    /**
     * Update the passed user in the database.
     */
    public void update(UserEntity user) {
        entityManager.merge(user);
    }


}
