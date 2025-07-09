package com.authorized.authorized.adapter.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {
    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
