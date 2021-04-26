package com.example.onlinebookstore.controller.restapi;

import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.RekomendasiBuku;
import com.example.onlinebookstore.repository.RekomendasiBukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rekomendasi")
public class RekomendasiBukuApi {

    @Autowired
    private RekomendasiBukuRepository rekomendasiBukuRepository;

    @GetMapping
    public List<RekomendasiBuku> getAll() {
        return rekomendasiBukuRepository.findAll();
    }

    @PostMapping
    public RekomendasiBuku save(@RequestBody RekomendasiBuku rekomendasiBuku){
        return rekomendasiBukuRepository.save(rekomendasiBuku);
    }
}