package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.service.BookService;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<BookDto> getBookList() {
        List<Book> listBuku = bookRepository.findAllOrderById();
        List<BookDto> bookDto =
                listBuku.stream()
                        .map(book -> mapToDto(book))
                        .collect(Collectors.toList());
        return bookDto;
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping
    public BookDto saveOrEditBook(@RequestBody BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        book.setIdPengarang(bookDto.getIdPengarang());
        book.setIdPenerbit(bookDto.getIdPenerbit());
        book.setIdKategori(bookDto.getIdKategori());
        book = bookService.saveBookService(book);
        BookDto bookDto1 = mapToDto(book);

        return bookDto1;
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }


    private BookDto mapToDto(Book book){
        BookDto bookDto = modelMapper.map(book, BookDto.class);

        bookDto.setIdPengarang(book.getAuthor().getId());
        bookDto.setNamaPengarang(book.getAuthor().getNamaPengarang());

        bookDto.setIdKategori(book.getCategory().getId());
        bookDto.setNamaKategori(book.getCategory().getNamaKategori());

        bookDto.setIdPenerbit(book.getPublisher().getId());
        bookDto.setNamaPenerbit(book.getPublisher().getNamaPenerbit());

        bookDto.setId(book.getId());

        return bookDto;
    }
//    private TransactionDto mapToDto(Keranjang keranjang){
//        BookDto bookDto = modelMapper.map(book, BookDto.class);
//
//        bookDto.setIdPengarang(book.getAuthor().getId());
//        bookDto.setNamaPengarang(book.getAuthor().getNamaPengarang());
//
//        bookDto.setIdKategori(book.getCategory().getId());
//        bookDto.setNamaKategori(book.getCategory().getNamaKategori());
//
//        bookDto.setIdPenerbit(book.getPublisher().getId());
//        bookDto.setNamaPenerbit(book.getPublisher().getNamaPenerbit());
//
//        bookDto.setId(book.getId());
//
//        return bookDto;
//    }

}

