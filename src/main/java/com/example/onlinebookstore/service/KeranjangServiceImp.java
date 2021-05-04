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
        if (dto.getId()==null) {   //kondisi jika buku sudah ada dan id di dto null maka tambah kuantitas
            Keranjang keranjang1 = keranjangRepository.findByIdBuku(dto.getIdBuku());
            keranjang1.setKuantitasBuku(dto.getKuantitasBuku());
            Long kuantitas1 = Long.valueOf(keranjang1.getKuantitasBuku());
            keranjang1.setSubTotalHargaBuku(harga * kuantitas1);
            keranjang1.setIdCustomer(keranjang.getIdCustomer());
            keranjangRepository.save(keranjang1);
        }
        else if (keranjangRepository.findIdBukuKeranjang(dto.getIdBuku())) {   //kondisi jika buku sudah ada di keranjang maka tambah kuantitas
            Keranjang keranjang2 = keranjangRepository.findByIdBuku(dto.getIdBuku());
            keranjang2.setKuantitasBuku(keranjang2.getKuantitasBuku() + dto.getKuantitasBuku());
            Long kuantitas2 = Long.valueOf(keranjang2.getKuantitasBuku());
            keranjang2.setSubTotalHargaBuku(harga * kuantitas2);
            keranjang2.setIdCustomer(keranjang.getIdCustomer());
            keranjangRepository.save(keranjang2);
        } else {                            //kondisi jika belum ada buku di keranjang / buat baru
            keranjang.setKuantitasBuku(1);
            keranjang.setSubTotalHargaBuku(harga);
            keranjang.setIdCustomer(keranjang.getIdCustomer());
            keranjang.setStatus("BELUM_BAYAR");
            keranjangRepository.save(keranjang);
        }
    }
}