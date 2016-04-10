package com.leafchild.springUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
public class SpringUsersApplication {

	public static void main(String[] args) {

		//Session session = HibernateUtil.getSessionFactory().openSession();

		//session.beginTransaction();
		//session.close();
		SpringApplication.run(SpringUsersApplication.class, args);
	}
}
