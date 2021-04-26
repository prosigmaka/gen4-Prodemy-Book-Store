package com.example.onlinebookstore.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = CheckoutItem.CHECKOUTITEM)
@Data
public class CheckoutItem {
    public static final String CHECKOUTITEM = "checkout_item";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CHECKOUTITEM)
    @SequenceGenerator(name = CHECKOUTITEM, sequenceName = "checkout_item_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

//    @Column(name="status_pesanan", nullable = false)
//    private String statusPesanan;

    @OneToOne
    @JoinColumn(name = "id_keranjang", updatable = false, insertable = false)
    private Keranjang keranjang;
    @Column(name="id_keranjang", nullable = false)
    private Integer idKeranjang;

//    @ManyToOne
//    @JoinColumn(name = "id_user", updatable = false, insertable = false)
//    private User user;
//    @Column(name="id_user", nullable = false)
//    private Integer idUser;

}