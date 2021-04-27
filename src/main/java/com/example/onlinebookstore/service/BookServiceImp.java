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
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private AuthorService authorService;


    @Override
    public Book saveBookService(Book book) {

        book = bookRepository.save(book);
        book.setAuthor(authorRepository.findById(book.getIdPengarang()).get());
        book.setCategory(categoryRepository.findById(book.getIdKategori()).get());
        book.setPublisher(publisherRepository.findById(book.getIdPenerbit()).get());

        return book;
    }

    @Override
    public Book manageDetailBookService(Book book, BookDto bookDto) {
        bookDto.setIdPengarang();
        bookDto.setIdPenerbit(null);
        bookDto.setIdKategori(null);

        List<Author> authors = authorRepository.findAll();
        for (Author author1 : authors) {
            if (author1.getNamaPengarang().equals(bookDto.getNamaPengarang())) {
                book.setIdPengarang(author1.getId());
                bookDto.setIdPengarang(book.getIdPengarang());
            }
        }

        List<Publisher> publishers = publisherRepository.findAll();
        for (Publisher publisher1 : publishers) {
            if (publisher1.getNamaPenerbit().equals(bookDto.getNamaPenerbit())) {
                book.setIdPenerbit(publisher1.getId());
                bookDto.setIdPenerbit(publisher1.getId());
            }
        }

        List<Category> categories = categoryRepository.findAll();
        for (Category category1 : categories) {
            if (category1.getNamaKategori().equals(bookDto.getNamaKategori())) {
                book.setIdKategori(category1.getId());
                bookDto.setIdKategori(category1.getId());
            }
        }

        return book;
    }


}