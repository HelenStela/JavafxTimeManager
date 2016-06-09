package ru.kpfu.itis.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author Gataullin Kamil
 * 16.08.2014 22:15:16
 */
@Entity                    // указывает на то, что данный класс является сущностью
@Table(name="Customer")    // задает имя таблицы, в которой будут храниться объекты класса
public class Customer implements Comparable {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)  // указываем, что email должен быть уникальным
    private String email;

    @OneToOne                          // определяет отношение один к одному
    @JoinColumn(name = "address_id")   // связка с таблицей Address будет происходить по столбцу address_id в таблице Customer
    private Address address;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy="customer")
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int compareTo(Object o) {
        Customer c = (Customer) o;
        System.out.println("Customer compareTo method for " + id + " and " + c.getId());
        return (int) (id - c.getId());
    }
}
