package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.model.entity.Author;
import com.example.onlinebookstore.model.entity.Book;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

public interface AuthorService {
    Author saveAuthorService(Author author);
}
