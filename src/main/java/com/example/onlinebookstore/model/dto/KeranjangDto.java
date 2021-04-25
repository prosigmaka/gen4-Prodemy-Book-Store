package com.example.onlinebookstore.model.dto;

import lombok.Data;
@Data
public class KeranjangDto {
    private Integer id;
    private String NamaCustomer;
    private String judulBuku;
    private Integer kuantitasBuku;
    private String kategoriBuku;
    private Long hargaBuku;
    private Long subTotalHargaBuku;

    private Integer idCustomer;
    private Integer idBuku;

}
