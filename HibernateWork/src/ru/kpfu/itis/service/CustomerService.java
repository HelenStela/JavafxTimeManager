package ru.kpfu.itis.service;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import ru.kpfu.itis.domain.Customer;
import ru.kpfu.itis.domain.Good;
import ru.kpfu.itis.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Запросы к заказчикам с использованием SQL (не желательный способ)
 *
 * @author Gataullin Kamil
 *         10.11.2014 0:14
 */
public class CustomerService {

    public List<Customer> getAllCustomers() {
        Session session = null;
        List<Customer> customers = new ArrayList<Customer>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            customers = session
                    .createSQLQuery("select * from Customer")   // задаем SQL запрос
                    .addEntity(Customer.class)        // указываем сущность ожидаемую в ответе
                    .list();                 // помещаем результаты в список
        } catch (Exception e) {
            System.err.println("Error in getAllCustomers(): " + e.getMessage());
        }
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Session session = null;
        Customer customer = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery sqlQuery = session.createSQLQuery("select * from Customer where id = ?") // задаем SQL запрос
                    .addEntity(Customer.class);                  // указываем сущность ожидаемую в ответе
            Query query = sqlQuery.setLong(1, id);               // подставляем параметры
            customer = (Customer) query.uniqueResult();          // получаем единственный результат
        } catch (Exception e) {
            System.err.println("Error in getCustomerById(): " + e.getMessage());
        }
        return customer;
    }

    public List<Customer> getCustomers(String likeName, String likeEmail) {
        Session session = null;
        List<Customer> customers = new ArrayList<Customer>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery sqlQuery = session.createSQLQuery("select * from Customer where LOWER(name) like :name and LOWER(email) like :email")
                    .addEntity(Customer.class);
            customers = sqlQuery.setString("name", likeName.toLowerCase())
                    .setString("email", likeEmail.toLowerCase())           // подставление значения параметра по имени
                    .list();
        } catch (Exception e) {
            System.err.println("Error in getAllCustomers(): " + e.getMessage());
        }
        return customers;
    }

    public void addCustomer(Customer customer) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // получение сессии
            session.beginTransaction();                                 // открытие транзакции
            session.save(customer);                                     // сохранение объекта
            session.getTransaction().commit();                          // выполнение транзакции
        } catch (Exception e) {
            System.err.println("Error in addCustomer(): " + e.getMessage());
            if (session != null && session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
