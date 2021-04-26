package com.example.onlinebookstore.service;

import com.example.onlinebookstore.controller.restapi.RequestOrder;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.model.entity.PesananStatus;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class CheckoutOrderServiceImp implements CheckoutOrderService{

    @Autowired
    CheckoutOrderRepository checkoutOrderRepository;

    @Autowired
    CheckoutItemRepository checkoutItemRepository;

    @Autowired
    KeranjangRepository keranjangRepository;



    @Override
    public Iterable<CheckoutOrder> getAllOrders() {
        return checkoutOrderRepository.findAll();
    }

    @Override
    public CheckoutOrder insertOrder(RequestOrder requestOrder) {
        Object keys[] = requestOrder.getKeranjang().keySet().toArray();
        int keranjang_index[] = new int[keys.length];
        for (int i = 0; i < keranjang_index.length; i++){
            keranjang_index[i] = (Integer) requestOrder.getKeranjang().keySet().toArray()[i];
        }
        CheckoutOrder checkoutOrder = new CheckoutOrder();
        double total = 0.0;
        for (int i = 0; i < keranjang_index.length; i++){
            Optional<Keranjang> keranjang = keranjangRepository.findById(keranjang_index[i]);
            Keranjang k = keranjang.get();
            CheckoutItem checkoutItem = new CheckoutItem();
            checkoutItem.setKeranjang(k);
            total += k.getSubTotalHargaBuku();
            checkoutOrder.getItems().add(checkoutItem);
            checkoutItemRepository.save(checkoutItem);
        }
        checkoutOrder.setTanggalCi(new Date());
        checkoutOrder.setTotalHargalCi(total);
        checkoutOrder.setTipePembayaran("Transfer Bank");
        checkoutOrder.setBatasTanggalPembayaran(new Date());
        checkoutOrder.setStatusPesanan(PesananStatus.BELUM_BAYAR);
        return checkoutOrderRepository.save(checkoutOrder);
    }
}
