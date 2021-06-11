package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.entity.*;
import com.example.onlinebookstore.repository.*;
import com.example.onlinebookstore.service.BookService;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
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
    private KeranjangRepository keranjangRepository;
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
    public BookDto getById(@PathVariable Integer id) {
        Book book = bookRepository.findById(id).get();
        BookDto bookData = mapToDto(book);
        return bookData;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/save")
    public BookDto saveOrEditBook(@RequestPart(value="data", required = true) BookDto bookDto, @RequestPart(value="file", required = true) MultipartFile file) throws Exception {
        Book book = modelMapper.map(bookDto, Book.class);
        String userFolderPath = "C:/Users/Lenovo/STORE/";
        Path path = Paths.get(userFolderPath);
        Path filePath = path.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        book.setGambar(file.getOriginalFilename());
        Author author = new Author();
        Category category = new Category();
        Publisher publisher = new Publisher();
        book = bookService.manageDetailBookService(book, bookDto);

        if (bookDto.getIdPengarang() == null) {
            author.setNamaPengarang(bookDto.getNamaPengarang());
            author = authorRepository.save(author);
            book.setIdPengarang(author.getId());
        }

        if (bookDto.getIdKategori() == null) {
            category.setNamaKategori(bookDto.getNamaKategori());
            category = categoryRepository.save(category);
            book.setIdKategori(category.getId());
        }

        if (bookDto.getIdPenerbit() == null) {
            publisher.setNamaPenerbit(bookDto.getNamaPenerbit());
            publisher = publisherRepository.save(publisher);
            book.setIdPenerbit(publisher.getId());
        }

        book = bookService.saveBookService(book);

        BookDto bookDto1 = mapToDto(book);
        return bookDto1;
    }

    @GetMapping("/getImage/{id}")
    public String getImage(@PathVariable Integer id) throws IOException {
        Book book = bookRepository.findById(id).get();
        String userFolderPath = "C:/Users/Lenovo/STORE/";
        String pathFile = userFolderPath + book.getGambar();
        Path paths = Paths.get(pathFile);
        byte[] filePhoto = Files.readAllBytes(paths);
        String encodedFile = Base64.getEncoder().encodeToString(filePhoto);
        return encodedFile;
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        if(keranjangRepository.getIdKeranjang(id) == null) {
            bookRepository.deleteById(id);
        } else {
            keranjangRepository.deleteById(keranjangRepository.getIdKeranjang(id));
            bookRepository.deleteById(id);
        }
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
        String key = "\\y" + keyword + "\\y"; //regex
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

    @GetMapping("/best-seller")
    public List<BookDto> listBestSeller() {
        List<Integer> listBookId = keranjangRepository.findBestSeller();
        List<BookDto> listBestSellerBook = new ArrayList<>();
        for (Integer id : listBookId) {
            Book book = bookRepository.findById(id).get();
            BookDto bestSellerBookDto = modelMapper.map(book, BookDto.class);
            listBestSellerBook.add(bestSellerBookDto);
        }
        return listBestSellerBook;
    }
}

