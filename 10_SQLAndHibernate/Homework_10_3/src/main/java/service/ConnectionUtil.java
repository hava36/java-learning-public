package service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConnectionUtil {

    private static SessionFactory sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml").build()).getMetadataBuilder().build().getSessionFactoryBuilder().build();

    private ConnectionUtil() {

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
