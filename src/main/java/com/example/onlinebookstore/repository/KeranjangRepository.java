package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Integer>{

}
