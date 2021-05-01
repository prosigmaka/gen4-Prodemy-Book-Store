package com.example.onlinebookstore.model.dto;

import lombok.Data;
import java.util.List;
@Data
public class CartUpdateDto {
    private List<DirectAddToCartDto> addToCart;
}
