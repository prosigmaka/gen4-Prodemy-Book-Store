package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.model.entity.Author;
import org.springframework.scheduling.annotation.EnableScheduling;

public interface AuthorService {
    Author saveAuthorService(BookDto bookDto);
}
