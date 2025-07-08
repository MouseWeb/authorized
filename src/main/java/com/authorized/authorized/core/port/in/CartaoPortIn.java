package com.authorized.authorized.core.port.in;

import com.authorized.authorized.core.domain.Cartao;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartaoPortIn {
    Optional<Cartao> criarCartao(Cartao cartao);
    Optional<BigDecimal> obterSaldo(String numeroCartao);
}
