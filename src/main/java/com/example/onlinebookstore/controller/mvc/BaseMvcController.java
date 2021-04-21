package com.example.onlinebookstore.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
    //    dashboard
    @GetMapping("home")
    public String home() {
        return "home/index";
    }

    @GetMapping("aboutus")
    public String aboutus() {
        return "aboutus/index";
    }


}