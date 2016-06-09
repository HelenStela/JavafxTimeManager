package ru.kpfu.itis.util;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Gataullin Kamil
 *         09.11.2014 0:25
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = configureSessionFactory();
    private static ServiceRegistry serviceRegistry;

    /**
     * Создание фабрики
     *
     * @return {@link SessionFactory}
     * @throws org.hibernate.HibernateException
     */
    private static SessionFactory configureSessionFactory() {
        try {
            // Создание SessionFactory с настройками из hibernate.cfg.xml
            Configuration configuration = new Configuration().configure("config/hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Получить фабрику сессий
     *
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
