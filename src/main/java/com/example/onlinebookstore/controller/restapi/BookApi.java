package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.service.BookService;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookApi {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

//    @GetMapping("/best-seller")
//    public List<Book> getAll() {
//        return bookRepository.findAll();
//    }
    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/{id}")
    public BookDto saveOrEditBook(@RequestBody BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        book.setIdPengarang(bookDto.getIdPengarang());
        book.setIdPenerbit(bookDto.getIdPenerbit());
        book.setIdKategori(bookDto.getIdKategori());
        return bookService.saveBookService(book);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }
}