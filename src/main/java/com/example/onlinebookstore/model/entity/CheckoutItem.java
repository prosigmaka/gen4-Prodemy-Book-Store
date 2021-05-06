package com.example.onlinebookstore.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = CheckoutItem.CHECKOUTITEM)
@Data
public class CheckoutItem {
    public static final String CHECKOUTITEM = "checkout_item";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CHECKOUTITEM)
    @SequenceGenerator(name = CHECKOUTITEM, sequenceName = "checkout_item_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="tanggal_ci", nullable = false)
    private Date tanggalCi;

//    @Column(name="status_pesanan", nullable = false)
//    private String statusPesanan;

    @ManyToOne
    @JoinColumn(name = "id_keranjang", updatable = false, insertable = false)
    private Keranjang keranjang;
    @Column(name="id_keranjang", nullable = false)
    private Integer idKeranjang;

    @ManyToOne
    @JoinColumn(name = "id_order", updatable = false, insertable = false)
    private CheckoutOrder checkoutOrder;
    @Column(name="id_order", nullable = false)
    private Integer idOrder;

//    @ManyToOne
//    @JoinColumn(name = "id_user", updatable = false, insertable = false)
//    private User user;
//    @Column(name="id_user", nullable = false)
//    private Integer idUser;

}