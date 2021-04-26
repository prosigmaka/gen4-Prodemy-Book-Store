package com.example.onlinebookstore.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = DetailTransaksiCK.DETAILTRANSAKSICK)
@Data
public class DetailTransaksiCK {
    public static final String DETAILTRANSAKSICK = "detailTransaksiCK";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DETAILTRANSAKSICK)
    @SequenceGenerator(name = DETAILTRANSAKSICK, sequenceName = "detailTransaksiCK_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="tanggal_ck", nullable = false)
    private LocalDateTime tanggalCk;
    @Column(name="total_harga_ck", nullable = false)
    private Integer totalHargalCk;
    @Column(name="tipe_pembayaran", nullable = false)
    private String tipePembayaran;
    @Column(name="status_pembayaran", nullable = false)
    private String statusPembayaran;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="batas_tanggal_pembayaran", nullable = false)
    private LocalDateTime batasTanggalPembayaran;

    @ManyToOne
    @JoinColumn(name = "id_user", updatable = false, insertable = false)
    private User user;
    @Column(name="id_user", nullable = false)
    private Integer idUser;


}