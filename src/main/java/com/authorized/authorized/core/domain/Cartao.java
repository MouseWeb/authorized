package com.authorized.authorized.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {
    @Id
    private String numeroCartao;
    private String senha;
    private BigDecimal saldo = new BigDecimal("500.00");

    @Version
    private Long version;
}
