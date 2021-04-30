package com.example.onlinebookstore.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DirectAddToCartDto {
    private Integer id;
    private Integer idBuku;
    private Integer kuantitasBuku;

}
