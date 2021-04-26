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
        book = manageAuthor(bookDto, book);
        book = managePublisher(bookDto, book);
        book = manageCategory(bookDto, book);
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
    private Book manageAuthor(BookDto bookDto, Book book){
        List<Author> authors = authorRepository.findAll();
        for(Author author:authors){
            if(author.getNamaPengarang().equals(bookDto.getNamaPengarang())){
                book.setIdPengarang(author.getId());
            }
        }
        return book;
    }

    private Book managePublisher(BookDto bookDto, Book book){
        List<Publisher> publishers = publisherRepository.findAll();
        for(Publisher publisher:publishers){
            if(publisher.getNamaPenerbit().equals(bookDto.getNamaPenerbit())){
                book.setIdPenerbit(publisher.getId());
            }
        }
        return book;
    }

    private Book manageCategory(BookDto bookDto, Book book){
        List<Category> categories = categoryRepository.findAll();
        for(Category category:categories){
            if(category.getNamaKategori().equals(bookDto.getNamaKategori())){
                book.setIdKategori(category.getId());
            }
        }
        return book;
    }



//    @GetMapping("/search/{keyword}")
//    public List<BookDto> listBookSearch(String keyword) {
//        List<Book> list = bookRepository.searchBook(keyword);
//        List<BookDto> bookDto = list.stream().map(book -> mapToDto(book)).collect(Collectors.toList());
//        return bookDto;
//    }
}

