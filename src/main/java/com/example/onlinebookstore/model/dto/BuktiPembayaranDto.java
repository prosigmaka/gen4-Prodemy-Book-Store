package com.example.onlinebookstore.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class BuktiPembayaranDto {
    private Integer id;
    private LocalDateTime tanggalPembayaran;
    private String gambarBuktiPembayaran;

    private Date tanggalOrder;
    private String tipePembayaran;
    private Long totalHargalCi;
    private Date batasTanggalPembayaran;
//    private Enum statusPesanan;
    
//    private List<CheckoutItemDto> items;
    private Integer idCo;
}
