package ru.kpfu.itis.domain;

import javax.persistence.*;

/**
 * Created by Olga on 09.06.2016.
 */
@Entity
@Table
public class Note {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @Column
    private String theme;

    @Column(nullable = false)
   private String text;

    @ManyToOne
    private PersonSimple personSimple;

}
