package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.entity.Book;
import org.springframework.scheduling.annotation.EnableScheduling;

public interface BookService {
    Book saveBookService(Book book);

}
