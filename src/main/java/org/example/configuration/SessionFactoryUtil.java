package org.example.configuration;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Service.class);
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(TransportService.class);
            configuration.addAnnotatedClass(PassengerService.class);
            configuration.addAnnotatedClass(Payment.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
