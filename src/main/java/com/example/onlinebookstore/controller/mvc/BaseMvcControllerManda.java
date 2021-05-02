package com.example.onlinebookstore.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcControllerManda {
    //    dashboard

//    @GetMapping("home")
//    public String dashboard() {
//        return "home/index";
//    }
//
//    @GetMapping("home-admin")
//    public String homepageAdm() {
//        return "admin/reporttransaction";
//    }

    @GetMapping("payment-information")
    public String paymentInfo() {
        return "Costumer/paymentInformation";
    }

    @GetMapping("checkout-trans")
    public String checkoutPage() {
        return "Costumer/shoppingCheckout";
    }

    @GetMapping("trans-list")
    public String transListPage() {
        return "Costumer/transactionList";
    }

    @GetMapping("pop-tans")
    public String popTransPage() {
        return "admin/poptransaction";
    }

    @GetMapping("report-tans")
    public String reportTransPage() {
        return "admin/reporttransaction";
    }


}
