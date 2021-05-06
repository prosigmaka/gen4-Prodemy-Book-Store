package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Integer>{
//
        @Query(value = "SELECT keranjang FROM Keranjang keranjang WHERE keranjang.id = ?1 AND keranjang.status = 'BELUM_BAYAR'", nativeQuery = false)
        List<Keranjang> findAllByIdKeranjang(Integer id);

        List<Keranjang> findAllByIdCustomer(Long id);

        Keranjang findByIdBukuAndIdCustomer(Integer idBuku, Long idCustomer);

        List<Keranjang> findAllByIdCustomerAndStatus(Long id, String status);
}
