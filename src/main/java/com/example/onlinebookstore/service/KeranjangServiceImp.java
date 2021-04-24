package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class KeranjangServiceImp implements KeranjangService {
    @Autowired
    private KeranjangRepository keranjangRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public Keranjang simpanKeKeranjang(Keranjang keranjang){


//        book = bookRepository.save(book);
//        book.setAuthor(authorRepository.findById(book.getIdPengarang()).get());
//        book.setCategory(categoryRepository.findById(book.getIdKategori()).get());
//        book.setPublisher(publisherRepository.findById(book.getIdPenerbit()).get());
//
//        return book;
        return keranjang;
    }
}