package ru.kpfu.itis.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Gataullin Kamil
 * 16.08.2014 22:21:45
 */
@Entity                  // указывает на то, что данный класс является сущностью
@Table(name="Orders")    // задает имя таблицы, в которой будут храниться объекты класса
public class Order {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    // @Column(name = "name") название колонки можно не указывать, тогда будет использовано название поля.
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne                                  // определяет отношение многие к одному
            (cascade = {CascadeType.REFRESH},
                    fetch = FetchType.LAZY)    // подгрузка объектов списка сразу
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany                                 // определяет отношение многие ко многим
            (cascade = CascadeType.REFRESH,
                    fetch = FetchType.LAZY)     // подгрузка объектов только при обращении к ним
    @JoinTable(name = "ORDERS_GOODS",           // вспомогательная связывающая таблица для отношений многие ко многим
            joinColumns = @JoinColumn(name = "ORDER_ID"),        // название колонки для связи с текущей таблицей Orders
            inverseJoinColumns = @JoinColumn(name = "GOOD_ID"))  // название колонки для связываемой таблицы Goods
    private List<Good> goods;

    public Order() {
    }

    public Order(String name, Date creationDate, Customer customer, List<Good> goods) {
        this.name = name;
        this.creationDate = creationDate;
        this.customer = customer;
        this.goods = goods;
    }

    public Order(Long id, String name, Date creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
