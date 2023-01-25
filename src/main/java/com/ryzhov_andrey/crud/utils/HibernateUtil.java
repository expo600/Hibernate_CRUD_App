package com.ryzhov_andrey.crud.utils;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (sessionFactory == null) {

            Flyway flyway = Flyway.configure().dataSource(properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")).load();
            flyway.baseline();
            flyway.migrate();

            try {
                Configuration configuration = new Configuration().configure();

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                configuration.addAnnotatedClass(Skill.class);
                configuration.addAnnotatedClass(Specialty.class);
                configuration.addAnnotatedClass(Developer.class);

                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Initial SessionFactory creation failed ... " + e);
            }

        }
        return sessionFactory;
    }
}