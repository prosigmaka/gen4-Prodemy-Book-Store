package com.example.onlinebookstore.controller.mvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("hc")
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

    @GetMapping( "login")
    public String login() {

        //authenticate the role of someone who logged in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String check = authentication.getAuthorities().toString();

        if(check.equals("[ROLE_USER]")){
            return "home/index";
        } else if(check.equals("[ROLE_ADMIN]")){
            return "home-company/index";
        }
        else {
            return "login";
        }
    }

//    @GetMapping("login")
//    public String login() { return "login"; }
}
