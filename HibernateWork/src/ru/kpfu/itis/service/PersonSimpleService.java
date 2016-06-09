package ru.kpfu.itis.service;

import org.hibernate.Session;
import ru.kpfu.itis.domain.PersonSimple;
import ru.kpfu.itis.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 09.06.2016.
 */
public class PersonSimpleService {

    public List<PersonSimple> getAllPersons(){
        Session session = null;
        List<PersonSimple> personSimples = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
           personSimples = (List<PersonSimple>) session.createQuery("from Note").list();
        }
        catch (Exception e){
            System.err.println("Error in getAllNotes(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return personSimples;
    }


    public List<PersonSimple> getPersons(String likeName){
        Session session = null;
        List<PersonSimple> personSimples = new ArrayList<>();
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            personSimples = session.createQuery("select new list (name) from Note n where n.name like :name").setString("name", likeName).list();
        }
        catch (Exception e){
            System.err.println("Error in getAllGoods(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return personSimples ;
    }




    public void addNote(PersonSimple personSimple) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // получение сессии
            session.beginTransaction();                                 // открытие транзакции
            session.save(personSimple);                                         // сохранение объекта
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
