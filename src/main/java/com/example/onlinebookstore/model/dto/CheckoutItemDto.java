package com.example.onlinebookstore.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class CheckoutItemDto {
    private String id;
    private Date tanggalCi;

    private String tipePembayaran;
    private String bankPilihan;

    private String NamaCustomer;
    private String judulBuku;
    private Integer kuantitasBuku;
    private String kategoriBuku;
    private Long hargaBuku;
    private Long subTotalHargaBuku;

    private Integer idKeranjang;

}
