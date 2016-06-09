package ru.kpfu.itis.domain;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Класс-модель для адресата (Person).
 *
 *
 */

public class Person {
      private Long id;


    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty address ;
    private StringProperty diagnos;
    private ObjectProperty<LocalDate> birthday;

    private final IntegerProperty seansNumber;
    private final IntegerProperty balance;

    private ListProperty<Note> notes;




    /**
     * Конструктор по умолчанию.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Конструктор с некоторыми начальными данными.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Какие-то фиктивные начальные данные для удобства тестирования.
        this.address = new SimpleStringProperty("какая-то улица");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        this.balance = new SimpleIntegerProperty(0);
        this.seansNumber = new SimpleIntegerProperty(1);
        this.id = Long.valueOf(-1);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getDiagnos() {
        return diagnos.get();
    }

    public StringProperty diagnosProperty() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos.set(diagnos);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public int getSeansNumber() {
        return seansNumber.get();
    }

    public IntegerProperty seansNumberProperty() {
        return seansNumber;
    }

    public void setSeansNumber(int seansNumber) {
        this.seansNumber.set(seansNumber);
    }

    public int getBalance() {
        return balance.get();
    }

    public IntegerProperty balanceProperty() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance.set(balance);
    }

    public ObservableList<Note> getNotes() {
        return notes.get();
    }

    public ListProperty<Note> notesProperty() {
        return notes;
    }

    public void setNotes(ObservableList<Note> notes) {
        this.notes.set(notes);
    }
}
