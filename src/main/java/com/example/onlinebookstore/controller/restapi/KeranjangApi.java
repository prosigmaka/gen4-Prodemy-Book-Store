package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.KeranjangService;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.dto.KeranjangDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class KeranjangApi {

    @Autowired
    private KeranjangService keranjangService;

    @Autowired
    private KeranjangRepository keranjangRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Keranjang> getAll() {
        return keranjangRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public Book getById(@PathVariable Integer id) {
//        return bookRepository.findById(id).get();
//    }

    @PostMapping()
    public Keranjang simpanKeranjang(@RequestBody KeranjangDto keranjangDto){
        Keranjang keranjang = modelMapper.map(keranjangDto, Keranjang.class);
       return keranjang;
    }
}