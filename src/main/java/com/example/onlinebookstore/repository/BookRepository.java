package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Book;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT book FROM Book book order by book.id", nativeQuery = false)
    List<Book> findAllOrderById();

    @Query(value = "SELECT * FROM book WHERE judul_buku ~* ?1", nativeQuery = true)
    List<Book> searchBook(String keyword);

    List<Book> findAllByIdKategori(Integer id);

    @Query(value = "select book.hargaBuku from Book book where book.id = ?1", nativeQuery = false)
    Long getHargaById(Integer id);

}