package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.RequestRecommendationBookDto;
import com.example.onlinebookstore.model.dto.ResponseRecommendationBookDto;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.entity.Category;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.RekomendasiBuku;
import com.example.onlinebookstore.repository.RekomendasiBukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookService bookService;

    private Long currentIdCustomer(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Long idCustomer = userRepository.findIdByUserName(userName);
        return idCustomer;
    }

    @GetMapping
    public List<ResponseRecommendationBookDto> getAll(Book book) {
        List<Book> bookList = bookService.recommendation(book);
        List<ResponseRecommendationBookDto> respRecBookDto = bookList.stream()
                .map(book1 -> mapToRespDto(book1))
                .collect(Collectors.toList());
        return respRecBookDto;


    }

    private ResponseRecommendationBookDto mapToRespDto(Book book){
        ResponseRecommendationBookDto resp = modelMapper.map(book, ResponseRecommendationBookDto.class);
        return resp;
    }

    @PostMapping
    public void save(@RequestBody RequestRecommendationBookDto requestRecommendationBookDto) {
        Book book = bookRepository.findById(requestRecommendationBookDto.getIdBuku()).get();
        RekomendasiBuku rekomendasiBuku = new RekomendasiBuku();
        rekomendasiBuku.setKategori(book.getCategory().getNamaKategori());
        rekomendasiBuku.setIdUser(currentIdCustomer());
        rekomendasiBukuRepository.save(rekomendasiBuku);
    }
}