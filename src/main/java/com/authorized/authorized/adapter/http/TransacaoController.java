package com.authorized.authorized.adapter.http;

import com.authorized.authorized.adapter.http.api.TransacaoApi;
import com.authorized.authorized.adapter.http.dto.TransacaoDTO;
import com.authorized.authorized.core.domain.enuns.StatusTransacao;
import com.authorized.authorized.core.port.in.TransacaoPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController implements TransacaoApi {

    private final TransacaoPortIn service;

    public TransacaoController(TransacaoPortIn service) {
        this.service = service;
    }

    @Override
    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> realizarTransacao(@RequestBody TransacaoDTO dto) {

        StatusTransacao status = service.realizarTransacao(
                dto.getNumeroCartao(),
                dto.getSenhaCartao(),
                dto.getValor()
        );

        HttpStatus code = (status == StatusTransacao.OK)
                ? HttpStatus.CREATED
                : HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity
                .status(code)
                .contentType(MediaType.TEXT_PLAIN)
                .body(status.name());
    }
}
