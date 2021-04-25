package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.dto.KeranjangDto;
import com.example.onlinebookstore.model.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Integer>{

}
