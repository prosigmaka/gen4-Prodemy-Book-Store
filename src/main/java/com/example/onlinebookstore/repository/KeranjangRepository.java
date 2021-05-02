package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Integer>{

    Optional<Keranjang> findById(Integer id);
}
