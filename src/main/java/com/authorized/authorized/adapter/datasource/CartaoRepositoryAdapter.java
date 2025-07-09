package com.authorized.authorized.adapter.datasource;

import com.authorized.authorized.adapter.datasource.database.entity.CartaoEntity;
import com.authorized.authorized.adapter.datasource.database.repository.CartaoRepository;
import com.authorized.authorized.core.domain.Cartao;
import com.authorized.authorized.core.port.out.CartaoPortOut;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartaoRepositoryAdapter implements CartaoPortOut {

    private final CartaoRepository repository;

    public CartaoRepositoryAdapter(CartaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cartao> findById(String numeroCartao) {
        return repository.findById(numeroCartao)
                .map(this::toDomain);
    }

    @Override
    public Optional<Cartao> findByNumeroCartaoForUpdate(String numeroCartao) {
        return repository.findByNumeroCartaoForUpdate(numeroCartao)
                .map(this::toDomain);
    }

    @Override
    public boolean existsById(String numeroCartao) {
        return repository.existsById(numeroCartao);
    }

    @Override
    public Cartao save(Cartao cartao) {
        CartaoEntity entity = toEntity(cartao);
        CartaoEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    private Cartao toDomain(CartaoEntity e) {
        return new Cartao(e.getNumeroCartao(), e.getSenha(), e.getSaldo(), e.getVersion());
    }

    private CartaoEntity toEntity(Cartao cartao) {
        return new CartaoEntity(
                cartao.getNumeroCartao(),
                cartao.getSenha(),
                cartao.getSaldo(),
                cartao.getVersion()
        );
    }
}
