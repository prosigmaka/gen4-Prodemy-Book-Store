package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.DirectAddToCartDto;
import com.example.onlinebookstore.model.entity.ItemStatus;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class KeranjangServiceImp implements KeranjangService {
    @Autowired
    private KeranjangRepository keranjangRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public void saveToCartDirect(Keranjang keranjang, DirectAddToCartDto dto) {
        List<Keranjang> cart = keranjangRepository.findAllByIdCustomer(keranjang.getIdCustomer());
        Long harga = bookRepository.getHargaById(dto.getIdBuku());
        if (cart != null) {                 // kondisi jika id user ada di database
            Keranjang cart1 = keranjangRepository.findByIdBukuAndIdCustomer(dto.getIdBuku(), keranjang.getIdCustomer());
            if (cart1 != null) {            // kondisi untuk modal cart maka update data
                cart1.setKuantitasBuku(dto.getKuantitasBuku());
                Long kuantitas1 = Long.valueOf(cart1.getKuantitasBuku());
                cart1.setSubTotalHargaBuku(harga * kuantitas1);
                keranjangRepository.save(cart1);
            } else {                        // kondisi jika id buku tidak ada dalam database maka buat data baru
                keranjang.setKuantitasBuku(1);
                keranjang.setSubTotalHargaBuku(harga);
                keranjang.setStatusKeranjang(ItemStatus.ADD_TO_CART);
                keranjangRepository.save(keranjang);
            }
        } else {                            // kondisi jika id user dan id buku tidak ada dalam database
            keranjang.setKuantitasBuku(1);
            keranjang.setSubTotalHargaBuku(harga);
            keranjang.setIdCustomer(keranjang.getIdCustomer());
            keranjang.setStatusKeranjang(ItemStatus.ADD_TO_CART);
            keranjangRepository.save(keranjang);
        }

    }
}