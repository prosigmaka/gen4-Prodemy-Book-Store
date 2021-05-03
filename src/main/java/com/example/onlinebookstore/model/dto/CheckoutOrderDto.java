package com.example.onlinebookstore.model.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CheckoutOrderDto {
    private Integer id;
    private Date tanggalCo;
    private String tipePembayaran;
    private String bankPilihan;
    private Long totalHargalCi;
    private Date batasTanggalPembayaran;
    private Enum statusPesanan;

    private List<CheckoutItemDto> items;
}
