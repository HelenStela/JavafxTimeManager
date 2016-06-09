package ru.kpfu.itis.domain;

import javax.persistence.*;

/**
 * @author Gataullin Kamil
 * 16.08.2014 22:16:42
 */
@Entity                 // указывает на то, что данный класс является сущностью
@Table(name="Good")     // задает имя таблицы, в которой будут храниться объекты класса
public class Good {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @Column(name = "name", nullable = false, length = 100) // название товара не может быть NULL, и его длина не более 100 символов
    private String name;

    @Column(name = "price")
    private Long price;

    public Good() {
    }

    public Good(String name, Long price) {
        this.name = name;
        this.price = price;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
