package ru.kpfu.itis.domain;

import javax.persistence.*;

/**
 * Created by Olga on 09.06.2016.
 */
@Entity
@Table (name = "Note")
public class Note {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
   private String text;

    @ManyToOne                                  // определяет отношение многие к одному
            (cascade = {CascadeType.REFRESH},
                    fetch = FetchType.LAZY)    // подгрузка объектов списка сразу
    private PersonSimple personSimple;

    public Note() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PersonSimple getPersonSimple() {
        return personSimple;
    }

    public void setPersonSimple(PersonSimple personSimple) {
        this.personSimple = personSimple;
    }

    public Note(String name, String text) {
        this.name = name;
        this.text = text;
    }
}
