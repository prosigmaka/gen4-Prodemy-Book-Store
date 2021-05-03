//package com.example.onlinebookstore.controller.mvc;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class LoginController {
//    //authenticate the role of someone who logged in
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String check = authentication.getAuthorities().toString();
//
//        if(check.equals("[ROLE_USER]")){
//        return "home";
//    } else if(check.equals("[ROLE_ADMIN]")){
//        return "hc";
//    }
//        else {
//        return "login";
//    }
//}
//
//}
