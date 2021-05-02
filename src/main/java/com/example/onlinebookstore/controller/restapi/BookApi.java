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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        if(bookDto.getIdPengarang()==null) {
            author.setNamaPengarang(bookDto.getNamaPengarang());
            author = authorRepository.save(author);
            book.setIdPengarang(author.getId());
        }

        if(bookDto.getIdKategori()==null) {
            category.setNamaKategori(bookDto.getNamaKategori());
            category = categoryRepository.save(category);
            book.setIdKategori(category.getId());
        }

        if(bookDto.getIdPenerbit()==null) {
            publisher.setNamaPenerbit(bookDto.getNamaPenerbit());
            publisher = publisherRepository.save(publisher);
            book.setIdPenerbit(publisher.getId());
        }

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


    @GetMapping("/search/{keyword}") //search
    public List<BookDto> listBookSearch(@PathVariable String keyword) {
        String key = "\\y"+keyword+"\\y"; //regex
        List<Book> list = bookRepository.searchBook(key);
        List<BookDto> bookDto = list.stream().map(book -> mapToDto(book)).collect(Collectors.toList());
        return bookDto;
    }

    @GetMapping("/page/{categories}")
    public List<BookDto> listBookCategories(@PathVariable String categories) {
        Integer idCategory = categoryRepository.getIdCategory(categories);
        List<Book> list = bookRepository.findAllByIdKategori(idCategory);
        List<BookDto> bookDto = list.stream().map(book -> mapToDto(book)).collect(Collectors.toList());
        return bookDto;
    }


}

