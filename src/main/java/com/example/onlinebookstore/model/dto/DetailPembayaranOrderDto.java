package com.example.onlinebookstore.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DetailPembayaranOrderDto {

    private String tipePembayaran;
    private String bankPilihan;

    private Integer idOrder;

}
