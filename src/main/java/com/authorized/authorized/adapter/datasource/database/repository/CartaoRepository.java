package com.authorized.authorized.adapter.datasource.database.repository;

import com.authorized.authorized.adapter.datasource.database.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<CartaoEntity, String> {
}
