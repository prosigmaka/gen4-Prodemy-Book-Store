package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.entity.*;
import com.example.onlinebookstore.model.dto.BookDto;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

public interface BookService {
    Book saveBookService(Book book);
    Book manageDetailBookService(Book book, BookDto bookDto);

    List<Book> recommendation(Book book);

}
