package com.example.onlinebookstore.model.dto;

import lombok.Data;

@Data
public class RequestRecommendationBookDto {
    private Integer id;
    private Integer idUser;
    private Integer idBuku;
}
