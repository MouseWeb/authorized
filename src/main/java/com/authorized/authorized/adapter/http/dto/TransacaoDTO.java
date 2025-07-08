package com.authorized.authorized.adapter.http.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoDTO {
    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
