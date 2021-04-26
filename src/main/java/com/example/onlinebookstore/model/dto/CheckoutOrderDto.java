package com.example.onlinebookstore.model.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CheckoutOrderDto {
    private Integer id;
    private LocalDateTime tanggalCi;
    private Integer tipePembayaran;
    private Integer totalHargalCi;
    private LocalDateTime batasTanggalPembayaran;
    private Enum statusPesanan;

    private List<CheckoutItemDto> items;
}
