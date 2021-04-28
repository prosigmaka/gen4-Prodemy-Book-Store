package com.example.onlinebookstore.model.dto;

import com.example.onlinebookstore.model.entity.Keranjang;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestOrderDTO {
//    private Integer userId;

    //<idKeranjang,totalPrice>
//    private Map<Integer, Integer> keranjang;

    //<idKeranjang>
    private List<Integer> cartsId;

//    {cartsId:{1,2,,}}

}
