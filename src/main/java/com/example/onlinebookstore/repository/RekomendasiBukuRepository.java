package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.RekomendasiBuku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RekomendasiBukuRepository extends JpaRepository<RekomendasiBuku, Integer> {
    @Query(value = "select * from RekomendasiBuku rek_buku order by kategori", nativeQuery = true)
    List<RekomendasiBuku> findAllOrderByCategory();


}
