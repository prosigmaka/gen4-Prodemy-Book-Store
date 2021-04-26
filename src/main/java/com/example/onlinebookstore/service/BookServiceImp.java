package com.example.onlinebookstore.service;


import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.AuthorRepository;
import com.example.onlinebookstore.repository.CategoryRepository;
import com.example.onlinebookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;


    @Override
    public Book saveBookService(Book book) {

        book = bookRepository.save(book);
        book.setAuthor(authorRepository.findById(book.getIdPengarang()).get());
        book.setCategory(categoryRepository.findById(book.getIdKategori()).get());
        book.setPublisher(publisherRepository.findById(book.getIdPenerbit()).get());

        return book;
    }


}