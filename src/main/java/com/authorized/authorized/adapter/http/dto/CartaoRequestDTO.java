package com.authorized.authorized.adapter.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoRequestDTO {
    private String numeroCartao;
    private String senha;
}
