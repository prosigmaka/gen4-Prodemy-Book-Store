package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Keranjang.CART)
@Data
public class Keranjang {
    public static final String CART = "keranjang";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CART)
    @SequenceGenerator(name = CART, sequenceName = "keranjang_seq", allocationSize = 1, initialValue = 1)

    private Integer id;
    @Column(name="kuantitas_buku", nullable = false)
    private Integer kuantitasBuku;
    @Column(name="subtotal_harga_buku", nullable = false)
    private Long subTotalHargaBuku;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private ItemStatus statusKeranjang;

//    @Column(name = "status", nullable = false)
//    private String status;



    @ManyToOne
    @JoinColumn(name = "id_buku", updatable = false, insertable = false)
    private Book book;
    @Column(name="id_buku", nullable = false)
    private Integer idBuku;

//    @ManyToOne
//    @JoinColumn(name = "id_customer", updatable = false, insertable = false)
//    private Customer customer;

    @Column(name="id_customer")
    private Integer idCustomer;

}