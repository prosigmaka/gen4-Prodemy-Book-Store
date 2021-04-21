package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Category.CATEGORY)
@Data
public class Category {
    public static final String CATEGORY = "cat";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CATEGORY)
    @SequenceGenerator(name = CATEGORY, sequenceName = "cat_seq")

    private Integer id;
    private String namaKategori;

}