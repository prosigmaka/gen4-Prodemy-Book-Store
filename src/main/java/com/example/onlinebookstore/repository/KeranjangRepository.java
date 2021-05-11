package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Integer> {
    //
    @Query(value = "SELECT keranjang FROM Keranjang keranjang WHERE keranjang.id = ?1 AND keranjang.statusKeranjang = 'ADD_TO_CART'", nativeQuery = false)
    List<Keranjang> findAllByIdKeranjang(Integer id);

    Optional<Keranjang> findById(Integer id);

    List<Keranjang> findAllByIdCustomer(Long id);

    Keranjang findByIdBukuAndIdCustomer(Integer idBuku, Long idCustomer);

    List<Keranjang> findAllByIdCustomerAndStatusKeranjang(Long id, String status);

    @Query(value = "SELECT keranjang.idBuku FROM Keranjang keranjang WHERE keranjang.statusKeranjang = 'PAID' GROUP BY keranjang.idBuku ORDER BY SUM(keranjang.kuantitasBuku) DESC", nativeQuery = false)
    List<Integer> findBestSeller();
}
