package com.example.onlinebookstore.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = CheckoutOrder.CHECKOUTORDER)
@Data
public class CheckoutOrder {
    public static final String CHECKOUTORDER = "checkout_order";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CHECKOUTORDER)
    @SequenceGenerator(name = CHECKOUTORDER, sequenceName = "checkout_order_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="tanggal_co", nullable = false)
    private Date tanggalCo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="tanggal_order")
    private Date tanggalOrder;
    @Column(name="total_harga_ci")
    private Long totalHargalCi;
    @Column(name="tipe_pembayaran")
    private String tipePembayaran;
    @Column(name="bank_pilihan")
    private String bankPilihan;
    @Enumerated(EnumType.STRING)
    @Column(name="status_pesanan", nullable = false)
    private PesananStatus statusPesanan;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="batas_tanggal_pembayaran")
    private Date batasTanggalPembayaran;

    @ManyToOne
    @JoinColumn(name = "id_costumer", updatable = false, insertable = false)
    private User user;
    @Column(name="id_costumer", nullable = false)
    private Long idCostumer;

//    @OneToMany(mappedBy = "checkoutOrder", cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_user", updatable = false, insertable = false)
//    private List<CheckoutItem> items = new ArrayList<CheckoutItem>();
//    @Column(name="id_user", nullable = false)
//    private Integer idUser;


}