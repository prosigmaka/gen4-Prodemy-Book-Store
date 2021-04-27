package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.entity.Author;
import com.example.onlinebookstore.model.entity.Category;
import com.example.onlinebookstore.repository.AuthorRepository;
import com.example.onlinebookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryApi {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Integer id) {
        return categoryRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        categoryRepository.deleteById(id);
    }
}