package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.ConteudoMapper;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoFiltroRequestDTO;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.model.entity.Conteudo;
import br.com.progirls.api.portal.repository.ConteudoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConteudoServiceTest {

	@Mock
	private ConteudoRepository conteudoRepository;

	@Mock
	private ConteudoMapper conteudoMapper;

	@InjectMocks
	private ConteudoService conteudoService;

	@Test
	@DisplayName("Deve listar conteúdos paginados")
	void deveListarConteudosPaginados() {
		Pageable pageable = PageRequest.of(0, 10);
		Conteudo conteudo = new Conteudo();
		conteudo.setTitulo("Título");
		Page<Conteudo> pageEntidades = new PageImpl<>(List.of(conteudo), pageable, 1);

		ConteudoResponseDTO dto = new ConteudoResponseDTO(
				1L,
				"Título",
				"Descrição",
				"https://example.com",
				"https://example.com/img.png",
				LocalDateTime.now(),
				null,
				null,
				List.of(),
				List.of()
		);

		when(conteudoRepository.findAll(pageable)).thenReturn(pageEntidades);
		when(conteudoMapper.toDto(conteudo)).thenReturn(dto);

		Page<ConteudoResponseDTO> resultado = conteudoService.listarConteudosPaginados(pageable);

		assertThat(resultado.getContent()).containsExactly(dto);
		assertThat(resultado.getTotalElements()).isEqualTo(1);
		verify(conteudoRepository).findAll(pageable);
		verify(conteudoMapper).toDto(conteudo);
	}

	@Test
	@DisplayName("Deve retornar página vazia ao listar conteúdos paginados quando não houver registros")
	void deveRetornarPaginaVaziaAoListarConteudosPaginados() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Conteudo> pageVazia = new PageImpl<>(List.of(), pageable, 0);

		when(conteudoRepository.findAll(pageable)).thenReturn(pageVazia);

		Page<ConteudoResponseDTO> resultado = conteudoService.listarConteudosPaginados(pageable);

		assertThat(resultado.getContent()).isEmpty();
		assertThat(resultado.getTotalElements()).isZero();
		verify(conteudoRepository).findAll(pageable);
		verifyNoInteractions(conteudoMapper);
	}

	@Test
	@DisplayName("Deve lançar IllegalArgumentException quando dataInicio for após dataFim")
	void deveLancarExcecaoQuandoDataInicioForAposDataFim() {
		Pageable pageable = PageRequest.of(0, 10);
		ConteudoFiltroRequestDTO filtrosInvalidos = new ConteudoFiltroRequestDTO(
				null,
				null,
				null,
				null,
				LocalDate.of(2026, 2, 1),
				LocalDate.of(2026, 1, 1)
		);

		assertThatThrownBy(() -> conteudoService.buscarConteudosComFiltros(pageable, filtrosInvalidos))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("data de início");

		verifyNoInteractions(conteudoRepository);
		verifyNoInteractions(conteudoMapper);
	}
}