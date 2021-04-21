package com.example.onlinebookstore.controller.restapi;

import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookApi {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

//    @GetMapping("/best-seller")
//    public List<Book> getAll() {
//        return bookRepository.findAll();
//    }


}