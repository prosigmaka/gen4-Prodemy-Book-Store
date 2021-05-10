package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = RekomendasiBuku.REK_BUKU)
@Data
public class RekomendasiBuku {
    public static final String REK_BUKU = "rek_buku";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = REK_BUKU)
    @SequenceGenerator(name = REK_BUKU, sequenceName = "rek_buku_seq", allocationSize = 1, initialValue = 1)

    private Integer id;
    @Column(name = "id_user", nullable = false)
    private Long idUser;
    @Column(name="kategori")
    private String kategori;

}