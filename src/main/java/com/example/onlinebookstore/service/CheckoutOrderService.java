package com.example.onlinebookstore.service;

import com.example.onlinebookstore.controller.restapi.RequestOrder;
import com.example.onlinebookstore.model.entity.CheckoutOrder;

public interface CheckoutOrderService {

    Iterable <CheckoutOrder> getAllOrders();
    CheckoutOrder insertOrder(RequestOrder requestOrder);

}
