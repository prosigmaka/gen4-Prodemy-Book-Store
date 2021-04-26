package com.example.onlinebookstore.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BuktiPembayaranDto {
    private Integer id;
    private LocalDateTime tanggalPembayaran;
    private String gambarBuktiPembayaran;

    private LocalDateTime tanggalCi;
    private Integer tipePembayaran;
    private Integer totalHargalCi;
    private LocalDateTime batasTanggalPembayaran;
    private Enum statusPesanan;
    
    private List<CheckoutItemDto> items;
    private Integer idCo;
}
