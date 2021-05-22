package com.example.onlinebookstore.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = BuktiPembayaran.BUKTIPEMBAYARAN)
@Data
public class BuktiPembayaran {
    public static final String BUKTIPEMBAYARAN = "bukti_pembayaran";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BUKTIPEMBAYARAN)
    @SequenceGenerator(name = BUKTIPEMBAYARAN, sequenceName = "bukti_pembayaran_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="tanggal_pembayaran", nullable = false)
    private LocalDateTime tanggalPembayaran;
//    @Column(name="gambar_bukti_pembayaran", nullable = false)
//    private String gambarBuktiPembayaran;


//    @ManyToOne
//    @JoinColumn(name = "id_user", updatable = false, insertable = false)
//    private User user;
//    @Column(name="id_user", nullable = false)
//    private Integer idUser;

    @OneToOne
    @JoinColumn(name = "id_co", updatable = false, insertable = false)
    private CheckoutOrder checkoutOrder;
    @Column(name="id_co", nullable = false)
    private Integer idCo;


}