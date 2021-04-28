package com.example.onlinebookstore.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onlinebookstore.service.BookService;




@Controller
@RequestMapping(value = "/")
public class SearchController {
    @Autowired
    private BookService bookService;

    @GetMapping("search-book")
    public String home(Model model, @RequestParam(defaultValue = "") String judulBuku) {
        model.addAttribute("search-book", bookService.findBookByTitle(judulBuku));
        return "search/index";
    }

}