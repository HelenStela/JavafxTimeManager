package ru.kpfu.itis.service;

import javafx.beans.property.ListProperty;
import org.hibernate.Session;
import org.hibernate.service.spi.SessionFactoryServiceInitiator;
import ru.kpfu.itis.domain.Note;
import ru.kpfu.itis.domain.Person;
import ru.kpfu.itis.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 09.06.2016.
 */
public class NoteService {


    public List<Note> getAllNotes(){
        Session session = null;
        List<Note> notes = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            notes = (List<Note>) session.createQuery("from Note").list();
        }
        catch (Exception e){
            System.err.println("Error in getAllNotes(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
       return notes;
    }


    public List<Note> getNotes(String likeName){
        Session session = null;
        List<Note> notes = new ArrayList<>();
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            notes = session.createQuery("select new list (name, text) from Note n where n.name like :name").setString("name", likeName).list();
        }
        catch (Exception e){
            System.err.println("Error in getAllGoods(): " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return notes;
    }




    public void addNote(Note note) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();  // получение сессии
            session.beginTransaction();                                 // открытие транзакции
            session.save(note);                                         // сохранение объекта
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
