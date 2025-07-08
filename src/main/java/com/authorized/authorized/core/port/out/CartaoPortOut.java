package com.authorized.authorized.core.port.out;

import com.authorized.authorized.core.domain.Cartao;

import java.util.Optional;

public interface CartaoPortOut {
    Optional<Cartao> findById(String numeroCartao);
    boolean existsById(String numeroCartao);
    Cartao save(Cartao cartao);
}
