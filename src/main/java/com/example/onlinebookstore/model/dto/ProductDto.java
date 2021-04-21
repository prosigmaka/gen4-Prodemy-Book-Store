package com.example.onlinebookstore.model.dto;
 import lombok.Data;

 @Data
public class ProductDto {
     private Integer id;
     private String judulBuku;
     private String tahunTerbit;
     private String stokBuku;
     private String hargaBuku;
     private String gambar;
     private String namaPengarang;
     private String namaKategori;
     private String namaPenerbit;

     private Integer idPengarang;
     private Integer idKategori;
     private Integer idPenerbit;

}
