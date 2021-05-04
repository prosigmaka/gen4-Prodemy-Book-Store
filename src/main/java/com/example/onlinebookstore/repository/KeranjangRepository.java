package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Integer>{
        @Query(value = "select exists(select * from keranjang where id_buku = ?1)", nativeQuery = true)
        Boolean findIdBukuKeranjang(Integer id);

        @Query(value = "select exists(select * from keranjang where id = ?1)", nativeQuery = true)
        Boolean findIdKeranjang(Integer id);

        Keranjang findByIdBuku(Integer id);

        List<Keranjang> findAllByIdCustomerAndStatus(Long id, String status);
}
