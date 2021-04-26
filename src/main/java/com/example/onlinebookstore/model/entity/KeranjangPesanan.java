package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = CheckoutKeranjang.CHECKOUTKERANJANG)
@Data
public class CheckoutKeranjang {
    public static final String CHECKOUTKERANJANG = "book";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CHECKOUTKERANJANG)
    @SequenceGenerator(name = CHECKOUTKERANJANG, sequenceName = "ck_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(name="status_pesanan", nullable = false)
    private String statusPesanan;

    @ManyToOne
    @JoinColumn(name = "id_user", updatable = false, insertable = false)
    private User user;
    @Column(name="id_user", nullable = false)
    private Integer idUser;

    @ManyToOne
    @JoinColumn(name = "id_detail_transaksi_ck", updatable = false, insertable = false)
    private DetailTransaksiCK detailTransaksiCK;
    @Column(name="id_detail_transaksi_ck", nullable = false)
    private Integer idDetailTransaksiCK;

    @ManyToOne
    @JoinColumn(name = "id_keranjang_pesanan", updatable = false, insertable = false)
    private Publisher publisher;
    @Column(name="id_penerbit", nullable = false)
    private Integer idPenerbit;
}