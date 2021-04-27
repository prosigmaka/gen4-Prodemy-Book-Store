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
    @SequenceGenerator(name = CATEGORY, sequenceName = "cat_seq", allocationSize = 1, initialValue = 1)

    private Integer id;
    @Column(name="nama_kategori", nullable=false)
    private String namaKategori;

}