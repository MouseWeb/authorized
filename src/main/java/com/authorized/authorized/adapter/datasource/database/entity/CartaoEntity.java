package com.authorized.authorized.adapter.datasource.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "cartoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoEntity {
    @Id
    private String numeroCartao;
    private String senha;
    private BigDecimal saldo;

    @Version
    private Long version;
}
