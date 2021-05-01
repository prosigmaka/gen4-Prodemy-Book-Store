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

    @GetMapping("home-company")
    public String hc() {
        return "home-company/index";
    }

    @GetMapping("product")
    public String product() {
        return "product/index";
    }

    @GetMapping("academic")
    public String academic(){
        return "academic/index";
    }
    @GetMapping("family")
    public String family(){
        return "family/index";
    }
    @GetMapping("novel")
    public String novel(){
        return "novel/index";
    }
    @GetMapping("comic")
    public String comic(){
        return "comic/index";
    }
}
