package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM Book book where stok_buku > ?1", nativeQuery = true)
    List<Book> findBestSeller(Integer stok);
}