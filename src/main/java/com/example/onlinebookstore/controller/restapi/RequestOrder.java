package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class RequestOrder {
//    private Integer userId;

    //<idKeranjang,totalPrice>
    private Map<Integer, Integer> keranjang;

}
