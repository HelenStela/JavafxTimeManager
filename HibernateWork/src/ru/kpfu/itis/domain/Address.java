package ru.kpfu.itis.domain;

import javax.persistence.*;

/**
 * @author Gataullin Kamil
 * 16.08.2014 22:12:57
 */
@Entity                   // указывает на то, что данный класс является сущностью
//@Table(name="Address")  название таблицы можно не указывать, тогда будет использовано название класса
public class Address {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50)
    private String street;
    
    @Column(length = 25)
    private String house;

    @Column(length = 25)
    private String flat;

    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(String city, String street, String house, String flat) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
