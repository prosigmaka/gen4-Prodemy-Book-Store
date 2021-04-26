package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.entity.CheckoutOrder;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.service.CheckoutOrderService;
import com.example.onlinebookstore.service.KeranjangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/co")
public class CheckoutOrderApi {
    @Autowired
    public CheckoutOrderService checkoutOrderService;

//    @Autowired
//    private CheckoutOrderService checkoutOrderService;


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<CheckoutOrder> getAllOrders() {
        return checkoutOrderService.getAllOrders();
    }

    @PostMapping(path = "/insert")
    public @ResponseBody
    CheckoutOrder insertOrder(@RequestBody RequestOrder requestOrder) {
        return checkoutOrderService.insertOrder(requestOrder);

    }
}