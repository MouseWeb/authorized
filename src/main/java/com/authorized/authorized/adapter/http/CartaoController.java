package com.authorized.authorized.adapter.http;

import com.authorized.authorized.adapter.http.api.CartaoApi;
import com.authorized.authorized.adapter.http.dto.CartaoRequestDTO;
import com.authorized.authorized.adapter.http.dto.CartaoResponseDTO;
import com.authorized.authorized.adapter.http.mapper.CartaoMapper;
import com.authorized.authorized.core.domain.Cartao;
import com.authorized.authorized.core.port.in.CartaoPortIn;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CartaoController implements CartaoApi {

    private final CartaoPortIn service;

    public CartaoController(CartaoPortIn service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CartaoResponseDTO> criarCartao(@Valid CartaoRequestDTO request) {
        Cartao domain = CartaoMapper.toDomain(request);

        Optional<Cartao> criado = service.criarCartao(domain);

        Cartao cartaoParaDto = criado.orElse(domain);
        CartaoResponseDTO response = CartaoMapper.toDTO(cartaoParaDto);

        HttpStatus status = criado.isPresent()
                ? HttpStatus.CREATED
                : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status).body(response);
    }

    @Override
    public ResponseEntity<BigDecimal> obterSaldoCartao(String numeroCartao) {
        return ResponseEntity.of(service.obterSaldo(numeroCartao));
    }

}
