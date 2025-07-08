package com.authorized.authorized.adapter.http.api;

import com.authorized.authorized.adapter.http.dto.TransacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "2. Transação", description = "Processamento de transações.")
@RequestMapping("/transacoes")
public interface TransacaoApi {

    @Operation(summary = "Realiza uma transação")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> realizarTransacao(@RequestBody @Valid TransacaoDTO dto);
}
