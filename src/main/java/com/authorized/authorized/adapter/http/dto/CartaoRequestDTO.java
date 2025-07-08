package com.authorized.authorized.adapter.http.dto;

import lombok.Data;

@Data
public class CartaoRequestDTO {
    private String numeroCartao;
    private String senha;
}
