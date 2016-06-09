package ru.kpfu.itis.service;

import org.hibernate.Session;
import ru.kpfu.itis.domain.Address;
import ru.kpfu.itis.domain.Customer;
import ru.kpfu.itis.util.HibernateUtil;

/**
 * @author Gataullin Kamil
 *         12.11.2014 23:12
 */
public class AddressService {

    public void addAddress(Address address) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // получение сессии
            session.beginTransaction();                                 // открытие транзакции
            session.save(address);                                      // сохранение объекта
            session.getTransaction().commit();                          // выполнение транзакции
        } catch (Exception e) {
            System.err.println("Error in addAddress(): " + e.getMessage());
            if (session != null && session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
