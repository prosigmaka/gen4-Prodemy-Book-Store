package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Author.AUTHOR)
@Data
public class Author {
    public static final String AUTHOR = "author";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AUTHOR)
    @SequenceGenerator(name = AUTHOR, sequenceName = "author_seq")

    private Integer id;
    private String namaPengarang;
    private String emailPengarang;
}