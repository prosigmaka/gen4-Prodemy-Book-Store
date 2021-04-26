package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.repository.CategoryRepository;
import com.example.onlinebookstore.repository.PublisherRepository;
import com.example.onlinebookstore.service.BookService;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.entity.Publisher;
import com.example.onlinebookstore.model.entity.Category;
import com.example.onlinebookstore.model.entity.Author;
import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.AuthorRepository;
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
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;
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
    public BookDto saveOrEditBook(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Author author = new Author();
        Category category = new Category();
        Publisher publisher = new Publisher();

        book = bookService.manageDetailBookService(book, bookDto);

        author.setId(bookDto.getIdPengarang());
        author.setNamaPengarang(bookDto.getNamaPengarang());
        authorRepository.save(author);

        category.setId(bookDto.getIdKategori());
        category.setNamaKategori(bookDto.getNamaKategori());
        categoryRepository.save(category);

        publisher.setId(bookDto.getIdPenerbit());
        publisher.setNamaPenerbit(bookDto.getNamaPenerbit());
        publisherRepository.save(publisher);

        book = bookService.saveBookService(book);

        BookDto bookDto1 = mapToDto(book);
        return bookDto1;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }


    private BookDto mapToDto(Book book) {
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



//    @GetMapping("/search/{keyword}")
//    public List<BookDto> listBookSearch(String keyword) {
//        List<Book> list = bookRepository.searchBook(keyword);
//        List<BookDto> bookDto = list.stream().map(book -> mapToDto(book)).collect(Collectors.toList());
//        return bookDto;
//    }
}

