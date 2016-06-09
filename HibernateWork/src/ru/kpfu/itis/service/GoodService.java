package ru.kpfu.itis.service;

import org.hibernate.Session;
import ru.kpfu.itis.domain.Good;
import ru.kpfu.itis.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Запросы к товарам с использованием HQL (допустимый способ)
 *
 * @author Gataullin Kamil
 *         10.11.2014 0:51
 */
public class GoodService {

    public List<Good> getAllGoods() {
        Session session = null;
        List<Good> goods = new ArrayList<Good>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // получаем список всех товаров отсортированных по цене
            goods = (List<Good>) session.createQuery("from Good order by price").list();
        } catch (Exception e) {
            System.err.println("Error in getAllGoods(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return goods;
    }

    public String getGoodNameById(Long id) {
        Session session = null;
        String goodName = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            goodName = (String) session.createQuery("select g.name from Good g where g.id = ?") // задаем HQL запрос
                    .setLong(0, id)                  // подставляем параметр
                    .uniqueResult();                 // получаем единственный результат
        } catch (Exception e) {
            System.err.println("Error in getGoodById(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return goodName;
    }

    public List<Good> getGoods(String likeName, Long minPrice) {
        Session session = null;
        List<Good> goods = new ArrayList<Good>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            goods = session.createQuery("select new list(g.id, name, g.price) from Good g " +
                    "where g.name like :name and g.price > :price order by price desc")
                    .setString("name", likeName)          // подставление значения параметра по имени
                    .setLong("price", minPrice)
                    .list();                              // получение списка товаров
        } catch (Exception e) {
            System.err.println("Error in getAllGoods(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return goods;
    }

    public void addGood(Good good) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // получение сессии
            session.beginTransaction();                                 // открытие транзакции
            session.save(good);                                         // сохранение объекта
            session.getTransaction().commit();                          // выполнение транзакции
        } catch (Exception e) {
            System.err.println("Error in addGood(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
