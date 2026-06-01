package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.entity.Usuario;
import br.com.progirls.api.portal.repository.UsuarioRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		usuarioRepository.deleteAll();

		Usuario user = new Usuario();
		user.setNome("Teste");
		user.setNomeUsuario("teste");
		user.setEmail("teste@teste.com");
		user.setSenha(passwordEncoder.encode("Teste@123"));
		user.setAtivo(true);

		usuarioRepository.save(user);
	}

	@Test
	void deveRetornarTokenComCredenciaisValidas() throws Exception {
		var response = mockMvc.perform(post("/api/v1/auth/login")
						.with(httpBasic("teste", "Teste@123")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").exists())
				.andReturn();

		String json = response.getResponse().getContentAsString();
		Map<String, Object> payload = objectMapper.readValue(json, new TypeReference<>() {});
		String token = (String) payload.get("token");

		mockMvc.perform(get("/api/v1/protected/hello")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	@Test
	void deveRetornar401ComCredenciaisInvalidas() throws Exception {
		mockMvc.perform(post("/api/v1/auth/login")
						.with(httpBasic("teste", "senhaErrada")))
				.andExpect(status().isUnauthorized());
	}
}