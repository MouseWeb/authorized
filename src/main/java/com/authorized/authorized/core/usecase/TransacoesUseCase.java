package com.authorized.authorized.core.usecase;

import com.authorized.authorized.core.domain.Cartao;
import com.authorized.authorized.core.domain.enuns.StatusTransacao;
import com.authorized.authorized.core.port.in.TransacaoPortIn;
import com.authorized.authorized.core.port.out.CartaoPortOut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransacoesUseCase implements TransacaoPortIn {

    private final CartaoPortOut repository;
    private BigDecimal valorTemp;

    public TransacoesUseCase(CartaoPortOut repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public StatusTransacao realizarTransacao(String numeroCartao, String senha, BigDecimal valor) {
        this.valorTemp = valor;
        return repository.findByNumeroCartaoForUpdate(numeroCartao)
                // cartão existe
                .map(cartao -> validarSenha(cartao, senha)
                        // senha correta
                        .map(c -> validarSaldo(c)
                                // saldo suficiente
                                .map(this::debitar)
                                // falhou no saldo
                                .orElse(StatusTransacao.SALDO_INSUFICIENTE))
                        // falhou na senha
                        .orElse(StatusTransacao.SENHA_INVALIDA))
                // falhou no cartão
                .orElse(StatusTransacao.CARTAO_INEXISTENTE);
    }

    private Optional<Cartao> validarSenha(Cartao cartao, String senha) {
        return Optional.of(cartao)
                .filter(c -> c.getSenha().equals(senha));
    }

    private Optional<Cartao> validarSaldo(Cartao cartao) {
        return Optional.of(cartao)
                .filter(c -> c.getSaldo().compareTo(valorTemp) >= 0);
    }

    private StatusTransacao debitar(Cartao cartao) {
        cartao.setSaldo(cartao.getSaldo().subtract(valorTemp));
        repository.save(cartao);
        return StatusTransacao.OK;
    }
}
