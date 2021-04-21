package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Publisher.PUBLISHER)
@Data
public class Publisher {
    public static final String PUBLISHER = "publisher";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PUBLISHER)
    @SequenceGenerator(name = PUBLISHER, sequenceName = "publisher_seq")

    private Integer id;
    private String namaPenerbit;
    private String alamatPenerbit;
    private String teleponPenerbit;
    private String emailPenerbit;
}