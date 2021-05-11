package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.RekomendasiBuku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RekomendasiBukuRepository extends JpaRepository<RekomendasiBuku, Integer>{

    List<RekomendasiBuku> findRekomendasiBukuByKategori(String str);

    Integer countRekomendasiBukuByKategori(String category);

}
