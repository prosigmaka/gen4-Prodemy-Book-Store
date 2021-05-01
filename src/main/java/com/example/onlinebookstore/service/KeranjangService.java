package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.DirectAddToCartDto;
import com.example.onlinebookstore.model.entity.Keranjang;

public interface KeranjangService {
    void saveToCartDirect(Keranjang keranjang, DirectAddToCartDto dto);
}
