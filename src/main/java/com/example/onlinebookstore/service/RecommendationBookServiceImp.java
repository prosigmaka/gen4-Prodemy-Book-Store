package com.example.onlinebookstore.service;


import com.example.onlinebookstore.model.entity.Category;
import com.example.onlinebookstore.model.entity.RekomendasiBuku;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CategoryRepository;
import com.example.onlinebookstore.repository.RekomendasiBukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RecommendationBookServiceImp implements RecommendationBookService {
    @Autowired
    private RekomendasiBukuRepository rekomendasiBukuRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public RekomendasiBuku recommendationBook(RekomendasiBuku rekomendasiBuku){

        return rekomendasiBuku;
    }
}