package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.MembroEquipeMapper;
import br.com.progirls.api.portal.model.dto.equipe.MembroEquipeResponseDTO;
import br.com.progirls.api.portal.model.entity.MembroEquipe;
import br.com.progirls.api.portal.repository.MembroEquipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MembroEquipeServiceTest {

	@Mock
	private MembroEquipeRepository membroEquipeRepository;

	@Mock
	private MembroEquipeMapper membroEquipeMapper;

	@InjectMocks
	private MembroEquipeService membroEquipeService;

	private MembroEquipe membroEquipe;
	private MembroEquipeResponseDTO membroEquipeResponseDTO;

	@BeforeEach
	void setUp() {
		membroEquipe = new MembroEquipe();
		membroEquipe.setId(1L);
		membroEquipe.setNome("Ana");
		membroEquipe.setCargo("Dev");
		membroEquipe.setFotoUrl("https://exemplo.com/foto.jpg");
		membroEquipe.setLinkedinUrl("https://linkedin.com/in/foureyesdev");
		membroEquipe.setGithubUrl("https://github.com/foureyesdev");

		membroEquipeResponseDTO = new MembroEquipeResponseDTO(
				membroEquipe.getId(),
				membroEquipe.getNome(),
				membroEquipe.getCargo(),
				membroEquipe.getFotoUrl(),
				membroEquipe.getLinkedinUrl(),
				membroEquipe.getGithubUrl()
		);
	}

	@Test
	@DisplayName("Deve retornar lista de membros da equipe quando existirem registros")
	void deveRetornarListaDeMembrosDaEquipeQuandoExistiremRegistros() {
		when(membroEquipeRepository.findAllByOrderByNomeAsc()).thenReturn(List.of(membroEquipe));
		when(membroEquipeMapper.toDTO(membroEquipe)).thenReturn(membroEquipeResponseDTO);

		List<MembroEquipeResponseDTO> resultado = membroEquipeService.listar();

		assertThat(resultado)
				.hasSize(1)
				.containsExactly(membroEquipeResponseDTO);

		verify(membroEquipeRepository).findAllByOrderByNomeAsc();
		verify(membroEquipeMapper).toDTO(membroEquipe);
	}

	@Test
	@DisplayName("Deve retornar lista vazia quando não houver registros")
	void deveRetornarListaVaziaQuandoNaoHouverRegistros() {
		when(membroEquipeRepository.findAllByOrderByNomeAsc()).thenReturn(List.of());

		List<MembroEquipeResponseDTO> resultado = membroEquipeService.listar();

		assertThat(resultado).isEmpty();

		verify(membroEquipeRepository).findAllByOrderByNomeAsc();
		verifyNoInteractions(membroEquipeMapper);
	}

	@Test
	@DisplayName("Deve lançar exceção quando ocorrer erro ao buscar membros da equipe")
	void deveLancarExcecaoQuandoOcorrerErroAoBuscarMembrosDaEquipe() {
		when(membroEquipeRepository.findAllByOrderByNomeAsc())
				.thenThrow(new RuntimeException("Falha ao acessar banco"));

		assertThatThrownBy(() -> membroEquipeService.listar())
				.isInstanceOf(RuntimeException.class);

		verify(membroEquipeRepository).findAllByOrderByNomeAsc();
		verifyNoInteractions(membroEquipeMapper);
	}
}