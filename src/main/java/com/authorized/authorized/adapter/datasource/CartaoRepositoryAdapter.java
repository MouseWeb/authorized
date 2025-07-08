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
                .map(e -> new Cartao(
                        e.getNumeroCartao(),
                        e.getSenha(),
                        e.getSaldo(),
                        e.getVersion()
                ));
    }

    @Override
    public boolean existsById(String numeroCartao) {
        return repository.existsById(numeroCartao);
    }

    @Override
    public Cartao save(Cartao cartao) {
        CartaoEntity entity = new CartaoEntity(
                cartao.getNumeroCartao(),
                cartao.getSenha(),
                cartao.getSaldo(),
                cartao.getVersion()
        );
        CartaoEntity saved = repository.save(entity);
        return new Cartao(
                saved.getNumeroCartao(),
                saved.getSenha(),
                saved.getSaldo(),
                saved.getVersion()
        );
    }
}
