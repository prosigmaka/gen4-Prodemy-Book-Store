package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.DirectAddToCartDto;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class KeranjangServiceImp implements KeranjangService {
    @Autowired
    private KeranjangRepository keranjangRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public void saveToCartDirect(Keranjang keranjang, DirectAddToCartDto dto) {
        Long harga = bookRepository.getHargaById(dto.getIdBuku());
        if (keranjangRepository.findIdBukuKeranjang(dto.getId())) {   //kondisi jika buku sudah ada di keranjang maka tambah kuantitas
            Keranjang keranjang1 = keranjangRepository.findByIdBuku(dto.getIdBuku());
            keranjang1.setKuantitasBuku(keranjang1.getKuantitasBuku() + 1);
            Long kuantitas1 = Long.valueOf(keranjang1.getKuantitasBuku());
            keranjang1.setSubTotalHargaBuku(harga * kuantitas1);
            keranjangRepository.save(keranjang1);
        } else {                            //kondisi jika belum ada buku di keranjang / buat baru
            keranjang.setKuantitasBuku(1);
            keranjang.setSubTotalHargaBuku(harga);
            keranjangRepository.save(keranjang);
        }
    }
}