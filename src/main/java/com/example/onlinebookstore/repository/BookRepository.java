package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM Book book order by id", nativeQuery = true)
    List<Book> findAllOrderById();

    @Query(value = "SELECT * FROM Book book WHERE judul_buku ~* ?1", nativeQuery = true)
    List<Book> searchBook(String keyword);

    List<Book> findAllByIdKategori(Integer id);
}