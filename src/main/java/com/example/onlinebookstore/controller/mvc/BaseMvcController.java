package com.example.onlinebookstore.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
    //    dashboard
    @GetMapping("")
    public String home() {
        return "home/index";
    }

    @GetMapping("aboutus")
    public String aboutus() {
        return "aboutus/index";
    }

    @GetMapping("home-company")
    public String hc() {
        return "home-company/index";
    }

    @GetMapping("product")
    public String product() {
        return "product/index";
    }

    @GetMapping("login")
    public String login() { return "login"; }
}
