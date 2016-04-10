package com.leafchild.springUsers;

import com.leafchild.springUsers.entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by: leafchild
 * Project: spring_users
 * Date: 2/25/16
 * Time: 18:33
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = initializeSessionFactory();

    private static SessionFactory initializeSessionFactory() {
        Configuration cfg = new Configuration();

        cfg.addAnnotatedClass(UserEntity.class);

        return cfg.buildSessionFactory(new StandardServiceRegistryBuilder().build());
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
