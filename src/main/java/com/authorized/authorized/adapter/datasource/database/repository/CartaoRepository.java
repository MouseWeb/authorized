package com.authorized.authorized.adapter.datasource.database.repository;

import com.authorized.authorized.adapter.datasource.database.entity.CartaoEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<CartaoEntity, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM cartoes c WHERE c.numeroCartao = :num")
    Optional<CartaoEntity> findByNumeroCartaoForUpdate(@Param("num") String numeroCartao);

}
