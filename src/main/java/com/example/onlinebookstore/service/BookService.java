package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.dto.BookDto;
import org.springframework.scheduling.annotation.EnableScheduling;

public interface BookService {
    Book saveBookService(Book book);
    Book manageDetailBookService(Book book, BookDto bookDto);

}
