package com.authorized.authorized;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CartaoControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	private static final String BASE_CARTOES = "/cartoes";
	private static final String BASE_TX      = "/transacoes";
	private static final String NUM          = "1234123412341234";
	private static final String SENHA        = "1234";

	@Test
	@Order(1)
	void deveCriarCartao() throws Exception {
		String json = String.format(
				"{\"numeroCartao\": \"%s\", \"senha\": \"%s\"}", NUM, SENHA);
		mvc.perform(post(BASE_CARTOES)
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.numeroCartao").value(NUM))
				.andExpect(jsonPath("$.senha").value(SENHA));
	}

	@Test
	@Order(2)
	void deveRetornarSaldoRecemCriado() throws Exception {
		mvc.perform(get(BASE_CARTOES + "/" + NUM)
						.accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().string("500.00"));
	}

	@Test
	@Order(3)
	void deveRealizarDiversasTransacoesAteSaldoInsuficiente() throws Exception {
		// Debitar 200 duas vezes
		String tx = String.format(
				"{\"numeroCartao\": \"%s\", \"senhaCartao\": \"%s\", \"valor\": 200.00}",
				NUM, SENHA);
		for (int i = 0; i < 2; i++) {
			mvc.perform(post(BASE_TX)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.TEXT_PLAIN)
							.content(tx))
					.andExpect(status().isCreated())
					.andExpect(content().string("OK"));
		}
		// Verifica saldo = 100.00
		mvc.perform(get(BASE_CARTOES + "/" + NUM)
						.accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().string("100.00"));
		// Terceira transação deve falhar
		mvc.perform(post(BASE_TX)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.TEXT_PLAIN)
						.content(tx))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().string("SALDO_INSUFICIENTE"));
	}

	@Test
	@Order(4)
	void deveFalharSenhaInvalida() throws Exception {
		String txErr = String.format(
				"{\"numeroCartao\": \"%s\", \"senhaCartao\": \"0000\", \"valor\": 10.00}", NUM);
		mvc.perform(post(BASE_TX)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.TEXT_PLAIN)
						.content(txErr))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().string("SENHA_INVALIDA"));
	}

	@Test
	@Order(5)
	void deveFalharCartaoInexistente() throws Exception {
		String txNo = String.format(
				"{\"numeroCartao\": \"0000000000000000\", \"senhaCartao\": \"%s\", \"valor\": 10.00}", SENHA);
		mvc.perform(post(BASE_TX)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.TEXT_PLAIN)
						.content(txNo))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().string("CARTAO_INEXISTENTE"));
	}
}
