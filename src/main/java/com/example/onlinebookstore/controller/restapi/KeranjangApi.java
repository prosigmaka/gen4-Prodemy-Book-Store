package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.DirectAddToCartDto;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.BookRepository;
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
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Keranjang> getAll() {
        return keranjangRepository.findAll();
    }



    @PostMapping()
    public Keranjang simpanKeranjang(@RequestBody KeranjangDto keranjangDto){
        Keranjang keranjang = modelMapper.map(keranjangDto, Keranjang.class);
       return keranjang;
    }

    @PostMapping("/add-direct")
    public Keranjang simpanKeranjang(@RequestBody DirectAddToCartDto directAddToCartDto){
        Keranjang keranjang = modelMapper.map(directAddToCartDto, Keranjang.class);
        keranjangService.saveToCartDirect(keranjang, directAddToCartDto);
        return keranjang;
    }
}