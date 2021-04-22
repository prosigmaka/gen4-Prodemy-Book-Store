package com.example.onlinebookstore.controller.restapi;

import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.Author;
import com.example.onlinebookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/author")
public class AuthorApi {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    //    @GetMapping("/best-seller")
//    public List<Book> getAll() {
//        return bookRepository.findAll();
//    }
    @GetMapping("/{id}")
    public Author getById(@PathVariable Integer id) {
        return authorRepository.findById(id).get();
    }

}