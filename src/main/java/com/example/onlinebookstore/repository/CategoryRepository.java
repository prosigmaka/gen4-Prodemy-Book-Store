package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    @Query(value = "select cat.id from Category cat where cat.namaKategori = ?1", nativeQuery = false)
    Integer getIdCategory(String category);
}
