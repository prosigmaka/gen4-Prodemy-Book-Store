package com.example.onlinebookstore.model.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class IdKeranjangDTO {
    private Integer id;

    //<idKeranjang,totalPrice>
//    private Map<Integer, Integer> keranjang;

    //<idKeranjang>
//    private List<String> MahasiswaId;

//    {cartsId:{1,2,,}}

}