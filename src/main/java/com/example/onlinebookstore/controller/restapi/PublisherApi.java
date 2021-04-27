package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.entity.Category;
import com.example.onlinebookstore.model.entity.Publisher;
import com.example.onlinebookstore.repository.CategoryRepository;
import com.example.onlinebookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherApi {

    @Autowired
    private PublisherRepository publisherRepository;


    @GetMapping
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publisher getById(@PathVariable Integer id) {
        return publisherRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        publisherRepository.deleteById(id);
    }
}
