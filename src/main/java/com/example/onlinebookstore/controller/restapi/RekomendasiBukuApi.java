package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.RecommendationBookDto;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.entity.Category;
import com.example.onlinebookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.RekomendasiBuku;
import com.example.onlinebookstore.repository.RekomendasiBukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rekomendasi")
public class RekomendasiBukuApi {

    @Autowired
    private RekomendasiBukuRepository rekomendasiBukuRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<RekomendasiBuku> getAll(Category category, Book book) {

        List<RekomendasiBuku> rek = rekomendasiBukuRepository.findRekomendasiBukuByKategori("Science");
        return rek;


    }

    @PostMapping
    public void save(@RequestBody RecommendationBookDto recommendationBookDto) {
        Book book = bookRepository.findById(recommendationBookDto.getIdBuku()).get();
        RekomendasiBuku rekomendasiBuku = new RekomendasiBuku();
        rekomendasiBuku.setKategori(book.getCategory().getNamaKategori());
        rekomendasiBuku.setIdUser(1);
        rekomendasiBukuRepository.save(rekomendasiBuku);
    }
}