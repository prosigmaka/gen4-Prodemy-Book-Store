package com.example.onlinebookstore.service;


import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.model.entity.Author;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.entity.Category;
import com.example.onlinebookstore.model.entity.Publisher;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.AuthorRepository;
import com.example.onlinebookstore.repository.CategoryRepository;
import com.example.onlinebookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AuthorServiceImp implements AuthorService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;


    @Override
    public Author saveAuthorService(Author author) {

        return authorRepository.save(author);

    }

}