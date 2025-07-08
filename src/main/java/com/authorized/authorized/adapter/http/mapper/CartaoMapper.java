package com.authorized.authorized.adapter.http.mapper;

import com.authorized.authorized.adapter.http.dto.CartaoRequestDTO;
import com.authorized.authorized.adapter.http.dto.CartaoResponseDTO;
import com.authorized.authorized.core.domain.Cartao;

import java.math.BigDecimal;

public class CartaoMapper {

    public static Cartao toDomain(CartaoRequestDTO dto) {
        return new Cartao(
                dto.getNumeroCartao(),
                dto.getSenha(),
                // saldo inicial fixo, mas só será usado em criação
                new BigDecimal("500.00"),
                null
        );
    }

    public static CartaoResponseDTO toDTO(Cartao cartao) {
        return new CartaoResponseDTO(
                cartao.getNumeroCartao(),
                cartao.getSenha()
        );
    }
}
