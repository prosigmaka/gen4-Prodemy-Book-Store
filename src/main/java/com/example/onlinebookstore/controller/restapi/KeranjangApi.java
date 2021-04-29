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
import java.util.stream.Collectors;

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
    public List<KeranjangDto> getAll() {
        List<Keranjang> keranjangList = keranjangRepository.findAll();
        List<KeranjangDto> keranjangDtoList = keranjangList
                .stream()
                .map(cart -> mappingToKeranjangDto(cart))
                .collect(Collectors.toList());
        return keranjangDtoList;
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

    private KeranjangDto mappingToKeranjangDto(Keranjang keranjang){
        KeranjangDto keranjangDto = modelMapper.map(keranjang, KeranjangDto.class);
        keranjangDto.setJudulBuku(keranjang.getBook().getJudulBuku());
        keranjangDto.setHargaBuku(keranjang.getBook().getHargaBuku());
        return keranjangDto;
    }
}