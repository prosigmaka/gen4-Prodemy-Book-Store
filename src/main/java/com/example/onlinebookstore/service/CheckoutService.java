package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import com.example.onlinebookstore.model.dto.CostumerLoginDTO;
import com.example.onlinebookstore.model.dto.RequestListOrderDTO;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CheckoutService {

//    Iterable <CheckoutOrder> getAllOrders();

//    CheckoutOrder insertOrder(RequestListOrderDTO requestListOrderDTO);


//    CheckoutOrder insertOrder(CheckoutItem checkoutItem);

    CheckoutOrder checkoutOrder (RequestListOrderDTO requestListOrderDTO, CostumerLoginDTO costumerLoginDTO);

//    List<CheckoutItemDto> checkout(RequestListOrderDTO requestListOrderDTO);

    CheckoutOrder placeOrder(CheckoutItem checkoutItem,CostumerLoginDTO costumerLoginDTO);


}
