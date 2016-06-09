package ru.kpfu.itis.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import ru.kpfu.itis.domain.Order;
import ru.kpfu.itis.util.HibernateUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Запросы к заказам с использованием Criteria (предпочтительный способ)
 *
 * @author Gataullin Kamil
 *         09.11.2014 21:44
 */
public class OrderService {

    public void addOrder(Order order) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // получение сессии
            session.beginTransaction();                                 // открытие транзакции
            session.save(order);                                        // сохранение объекта
            session.getTransaction().commit();                          // выполнение транзакции
        } catch (Exception e) {
            System.err.println("Error in addOrder(): " + e.getMessage());
            if (session != null && session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateOrder(Order order) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);          // обновление объекта по его id
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in updateOrder(): " + e.getMessage());
            if (session != null && session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Order getOrderById(Long id) {
        Session session = null;
        Order order = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria crit = session.createCriteria(Order.class); // создаем критерий запроса
            crit.add(Restrictions.idEq(id));                     // добавляем условие на id
            order = (Order) crit.uniqueResult();                 // получаем единственный результат
        } catch (Exception e) {
            System.err.println("Error in getOrderById(): " + e.getMessage());
        }
        return order;
    }

    public List<Order> getAllOrders() {
        Session session = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria crit = session.createCriteria(Order.class);  // создаем критерий запроса
            // todo далее рассмотрим различные условия
            Timestamp lastWeek = new Timestamp((new Date()).getTime() - 1000 * 60 * 60 * 24 * 7);
            crit.add(Restrictions.like("name", "toy%"));          // схожесть указанного поля шаблону, где ‘%’ — любые символы

            crit.add(Restrictions.between("creation_date", lastWeek,
                    new Timestamp((new Date()).getTime())));      // значение поля находится между двумя указанными

            crit.add(Restrictions.or(                         // дизъюнкция (ИЛИ) для нескольких других условий, аналогично and (И)
                    Restrictions.eq("name", "car"),           // определяет равенство поля какому-то значению
                    Restrictions.isNull("creation_date")));   // значение поля равно NULL

            crit.add(Restrictions.in("name", new String[]{"car", "train", "bus"}));  // задает диапазон значений

            crit.add(Restrictions.conjunction()                   // конъюнкция (И) для нескольких других условий
                    .add(Restrictions.isNotNull("creation_date")) // значение поля не NULL
                    .add(Restrictions.ne("name", "hotel"))        // значение поля не равно "hotel"
                    .add(Restrictions.lt("creation_date", lastWeek)) // меньше (<) указанного значения; аналогично gt (>), le (<=), ge (>=).
            );
            // сортировать по возрастанию имени; аналогично desc по убыванию
            crit.addOrder(org.hibernate.criterion.Order.asc("name"));

            crit.setMaxResults(50);        // ограничиваем число результатов
            orders = crit.list();          // помещаем результаты в список
        } catch (Exception e) {
            System.err.println("Error in getAllOrders(): " + e.getMessage());
        }
        return orders;
    }

    public List<Order> getOrders(Order order) {
        Session session = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria crit = session.createCriteria(Order.class);  // создаем критерий запроса
            // todo задание условий через объект
            // !!! В данном случае поля объекта, имеющие значение null или являющиеся идентификаторами, будут игнорироваться.
            Example example = Example.create(order)
                    .excludeZeroes()           // исключает поля с нулевыми значениями
                    .excludeProperty("name")   // исключает поле "name"
                    .ignoreCase()              // задает независимое от регистра сравнение строк
                    .enableLike();             // использует like для сравнения строк
            crit.add(example);

            crit.addOrder(org.hibernate.criterion.Order.desc("name")); // сортировать по убыванию имени
            crit.setMaxResults(25);        // ограничиваем число результатов
            orders = crit.list();          // помещаем результаты в список
        } catch (Exception e) {
            System.err.println("Error in getAllOrders(): " + e.getMessage());
        }
        return orders;
    }

    public void deleteOrder(Order order) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in deleteOrder(): " + e.getMessage());
            if (session != null && session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}