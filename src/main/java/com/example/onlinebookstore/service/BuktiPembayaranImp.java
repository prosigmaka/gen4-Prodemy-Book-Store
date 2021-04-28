package com.example.onlinebookstore.service;


import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.entity.BuktiPembayaran;
import com.example.onlinebookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BuktiPembayaranImp implements BuktiPembayaranService {
    @Autowired
    private BuktiPembayaranRepository buktiPembayaranRepository;
    @Autowired
    private CheckoutOrderRepository checkoutOrderRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;


    @Override
    public BuktiPembayaran saveBuktiPembayaranService(BuktiPembayaran buktiPembayaran) {

        buktiPembayaran = buktiPembayaranRepository.save(buktiPembayaran);
        buktiPembayaran.setCheckoutOrder(checkoutOrderRepository.findById(buktiPembayaran.getIdCo()).get());

        return buktiPembayaran;
    }


}