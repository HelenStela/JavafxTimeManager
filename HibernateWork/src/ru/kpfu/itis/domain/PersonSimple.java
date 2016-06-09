package ru.kpfu.itis.domain;


import javafx.collections.ObservableList;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;

@Entity
@Table(name = "Person")
public class PersonSimple {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @Column
    private  String firstName;

    @Column
    private  String lastName;

    @Column
    private  String address;

    @Column
    private String diagnos;

    @Column
    private LocalDate birthday;

    @Column
    private Integer seansNumber;

    @Column
    private Integer balance;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY)
    private ObservableList<Note> noteList;

    /**
     * Конструктор по умолчанию.
     */
    public PersonSimple() {
        this(null, null);
    }

    /**
     * Конструктор с некоторыми начальными данными.
     *
     * @param firstName
     * @param lastName
     */
    public PersonSimple(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Какие-то фиктивные начальные данные для удобства тестирования.
        this.address="какая-то улица";
        this.birthday = LocalDate.of(1999, 2, 21);
        this.balance = 0;
        this.seansNumber = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getSeansNumber() {
        return seansNumber;
    }

    public void setSeansNumber(Integer seansNumber) {
        this.seansNumber = seansNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public ObservableList<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(ObservableList<Note> noteList) {
        this.noteList = noteList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
