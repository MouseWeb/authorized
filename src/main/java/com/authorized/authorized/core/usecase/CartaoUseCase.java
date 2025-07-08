package com.authorized.authorized.core.usecase;

import com.authorized.authorized.core.domain.Cartao;
import com.authorized.authorized.core.port.in.CartaoPortIn;
import com.authorized.authorized.core.port.out.CartaoPortOut;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartaoUseCase implements CartaoPortIn {

    private final CartaoPortOut repository;

    public CartaoUseCase(CartaoPortOut repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cartao> criarCartao(Cartao cartao) {
        return Optional.of(cartao)
                .filter(c -> !repository.existsById(c.getNumeroCartao()))
                .map(c -> {
                    c.setSaldo(new BigDecimal("500.00"));
                    return repository.save(c);
                });
    }

    @Override
    public Optional<BigDecimal> obterSaldo(String numeroCartao) {
        return repository.findById(numeroCartao)
                .map(Cartao::getSaldo);
    }
}
