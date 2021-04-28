package com.example.onlinebookstore.service;

import com.example.onlinebookstore.controller.restapi.RequestOrder;
import com.example.onlinebookstore.model.dto.RequestOrderDTO;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import org.springframework.web.bind.annotation.RequestBody;

public interface CheckoutOrderService {

    Iterable <CheckoutOrder> getAllOrders();
    CheckoutOrder insertOrder(RequestOrder requestOrder);
//    CheckoutItem checkoutItem(RequestOrder requestOrder);

}
