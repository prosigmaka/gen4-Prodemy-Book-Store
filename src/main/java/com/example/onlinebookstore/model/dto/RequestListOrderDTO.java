package com.example.onlinebookstore.model.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class RequestListOrderDTO {
//    private Integer userId;

    //<idKeranjang,totalPrice>
//    private Map<Integer, Integer> keranjang;

    //<idKeranjang>
    private List<IdKeranjangDTO> idKeranjangDTOList;




}
