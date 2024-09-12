package com.neoteric.fullstackdemo_31082024.hibernate;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.neoteric.fullstackdemo_31082024.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            Configuration configuration= new Configuration();
            Properties properties= new Properties();
            properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL,"jdbc:mysql://@localhost:3306/bank");
            properties.put(Environment.USER,"root");
            properties.put(Environment.PASS,"rakesh");
            properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
            properties.put(Environment.SHOW_SQL,true);


            configuration.setProperties(properties);
            configuration.addAnnotatedClass(AccountEntity.class)
                    .addAnnotatedClass(AdharEntity.class)
                    .addAnnotatedClass(AddressEntity.class)
                    .addAnnotatedClass(AccountAddressEntity.class);

            ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory= configuration.buildSessionFactory(serviceRegistry);

        }
        return sessionFactory;

    }


}
