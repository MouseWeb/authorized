package com.authorized.authorized.core.port.in;

import com.authorized.authorized.core.domain.enuns.StatusTransacao;

import java.math.BigDecimal;

public interface TransacaoPortIn {
    StatusTransacao realizarTransacao(String numeroCartao, String senha, BigDecimal valor);
}
