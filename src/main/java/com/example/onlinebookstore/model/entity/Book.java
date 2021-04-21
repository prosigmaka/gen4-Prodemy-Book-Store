package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Book.BOOK)
@Data
public class Book {
    public static final String BOOK = "book";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BOOK)
    @SequenceGenerator(name = BOOK, sequenceName = "book_seq", allocationSize = 1, initialValue = 1)

    private Integer id;
    @Column(name="judul_buku", nullable = false)
    private String judulBuku;
    @Column(name="tahun_terbit", nullable = false)
    private Integer tahunTerbit;
    @Column(name="stok_buku", nullable = false)
    private Integer stokBuku;
    @Column(name="harga_buku", nullable = false)
    private Long hargaBuku;
    @Column(name="gambar")
    private String gambar;

    @ManyToOne
    @JoinColumn(name = "id_kategori", updatable = false, insertable = false)
    private Category category;
    @Column(name="id_kategori", nullable = false)
    private Integer idKategori;

    @ManyToOne
    @JoinColumn(name = "id_pengarang", updatable = false, insertable = false)
    private Author author;
    @Column(name="id_pengarang", nullable = false)
    private Integer idPengarang;

    @ManyToOne
    @JoinColumn(name = "id_penerbit", updatable = false, insertable = false)
    private Publisher publisher;
    @Column(name="id_penerbit", nullable = false)
    private Integer idPenerbit;
}