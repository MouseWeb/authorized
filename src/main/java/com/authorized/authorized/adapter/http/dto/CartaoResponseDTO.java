package com.authorized.authorized.adapter.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartaoResponseDTO {
    private String numeroCartao;
    private String senha;
}
