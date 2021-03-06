package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.CheckoutOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// interface sehingga hanya berisi abstract method
/*
    JpaRepository<1?, 2?>
    1? = berisi model entity
    2? = berisi tipe data primary key dari model entity*/
@Repository
public interface CheckoutOrderRepository extends JpaRepository<CheckoutOrder, Integer> {

    List<CheckoutOrder> findAll();


    Optional<CheckoutOrder> findById(Integer id);


    /* CARA PERTAMA
     * berdasarkan nama method*/

    // mencari list semua biodata where alamat =
    // ada juga yang langsung findByAlamat tanpa All
//    List<Biodata> findAllByAlamat(String address);

    // mencari suatu biodata where alamat =
//    Biodata findByAlamat(String address);

    // mencari list semua biodata where alamat = dan nama =
    // urutan parameter harus sama dengan urutan penyebutan di namanya
//    List<Biodata> findByAlamatAndNama(String alamat, String nama);

    /* CARA Kedua
     * menggunakan nativeQuery = false
     * yaitu menggunakan model entity sebagai querynya
     * nativeQuery jika tidak didefinisikan / tidak ditulis defaultnya adalah false*/


    // ?1 = adalah urutan parameter
//    @Query(value = "SELECT b FROM  Biodata b where b.alamat = ?1", nativeQuery = false)
//    List<Biodata> cariBerdasarkanAlamat(String address);

//    @Query(value = "SELECT b FROM  Biodata b where b.alamat = ?1 and b.nama = ?2", nativeQuery = false)
//    List<Biodata> cariBerdasarkanNamaDanAlamat(String alamat, String nama);


    /* CARA Ketiga
     * menggunakan nativeQuery = true
     * yaitu query sesuai dengan nama table dan column di database
     * */

    // ?1 = adalah urutan parameter
//    @Query(value = "SELECT * FROM  Biodata t_biodata t where t.alamat = ?1 ", nativeQuery = true)
//    List<Biodata> cariAlamat(String address);

//    @Query(value = "SELECT * FROM  Biodata t_biodata where t.alamat = ?1 and t.nama = ?2", nativeQuery = true)
//    List<Biodata> cariNamaDanAlamat(String alamat, String nama);
}
