package com.authorized.authorized.adapter.http.api;

import com.authorized.authorized.adapter.http.dto.CartaoRequestDTO;
import com.authorized.authorized.adapter.http.dto.CartaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Tag(name = "1. Cartão", description = "Gerenciamento de cartões.")
@RequestMapping("/cartoes")
public interface CartaoApi {

    @Operation(summary = "Cria um novo cartão")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = CartaoResponseDTO.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content(schema = @Schema(implementation = CartaoResponseDTO.class)))
    })
    @PostMapping
    ResponseEntity<CartaoResponseDTO> criarCartao(@RequestBody @Valid CartaoRequestDTO cartao);

    @Operation(summary = "Consultar saldo do cartão")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{numeroCartao}")
    ResponseEntity<BigDecimal> obterSaldoCartao(@PathVariable String numeroCartao);
}
