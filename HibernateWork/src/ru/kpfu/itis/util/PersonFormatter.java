package ru.kpfu.itis.util;


import ru.kpfu.itis.domain.Person;
import ru.kpfu.itis.domain.PersonSimple;

import java.time.LocalDate;

/**
 * Created by Olga on 09.06.2016.
 */
public class PersonFormatter {

    public Person personPropertyOf(PersonSimple personSimple) {
        Person person = new Person(personSimple.getFirstName(), personSimple.getLastName());
        person.setBirthday(personSimple.getBirthday());
        person.setBalance(personSimple.getBalance());
        person.setAddress(personSimple.getAddress());
        person.setSeansNumber(personSimple.getSeansNumber());
        person.setDiagnos(personSimple.getDiagnos());
        person.setNotes(personSimple.getNoteList());
        person.setId(personSimple.getId());

        return person;
    }


    public PersonSimple personSimpleOf(Person person){
        PersonSimple personSimple = new PersonSimple(person.getFirstName(),person.getLastName());
        personSimple.setId(person.getId());
        personSimple.setDiagnos(person.getDiagnos());
        personSimple.setAddress(person.getAddress());
        personSimple.setSeansNumber(person.getSeansNumber());
        personSimple.setNoteList(person.getNotes());
        return personSimple;
    }
        }

