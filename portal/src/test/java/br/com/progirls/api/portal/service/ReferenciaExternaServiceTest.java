package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.ReferenciaExternaMapper;
import br.com.progirls.api.portal.model.dto.referencia.ReferenciaExternaReponseDTO;
import br.com.progirls.api.portal.model.entity.ReferenciaExterna;
import br.com.progirls.api.portal.repository.ReferenciaExternaRepository;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReferenciaExternaServiceTest {

	@Mock
	private ReferenciaExternaRepository referenciaExternaRepository;

	@Mock
	private ReferenciaExternaMapper referenciaExternaMapper;

	@InjectMocks
	private ReferenciaExternaService referenciaExternaService;

	private ReferenciaExterna referenciaExterna;
	private ReferenciaExternaReponseDTO referenciaExternaDTO;

	@BeforeEach
	void setUp() {
		referenciaExterna = new ReferenciaExterna();
		referenciaExterna.setId(1L);
		referenciaExterna.setTitulo("Documentação Spring");
		referenciaExterna.setDescricao("Guia oficial do Spring Framework");
		referenciaExterna.setLink("https://spring.io/projects/spring-framework");

		referenciaExternaDTO = new ReferenciaExternaReponseDTO(
				referenciaExterna.getId(),
				referenciaExterna.getTitulo(),
				referenciaExterna.getDescricao(),
				referenciaExterna.getLink()
		);
	}

	@Test
	@DisplayName("Deve retornar paginação de referências quando houver registros")
	void deveRetornarPaginacaoDeReferenciasQuandoHouverRegistros() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<ReferenciaExterna> pageEntidades = new PageImpl<>(List.of(referenciaExterna), pageable, 1);

		when(referenciaExternaRepository.findAllByOrderByTituloAsc(pageable)).thenReturn(pageEntidades);
		when(referenciaExternaMapper.toDTO(referenciaExterna)).thenReturn(referenciaExternaDTO);

		Page<ReferenciaExternaReponseDTO> resultado = referenciaExternaService.listar(pageable);

		assertThat(resultado.getContent())
				.hasSize(1)
				.containsExactly(referenciaExternaDTO);
		assertThat(resultado.getNumber()).isEqualTo(0);
		assertThat(resultado.getSize()).isEqualTo(10);
		assertThat(resultado.getTotalElements()).isEqualTo(1);

		verify(referenciaExternaRepository).findAllByOrderByTituloAsc(pageable);
		verify(referenciaExternaMapper).toDTO(referenciaExterna);
	}

	@Test
	@DisplayName("Deve retornar content vazio quando não houver registros")
	void deveRetornarContentVazioQuandoNaoHouverRegistros() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<ReferenciaExterna> pageVazia = new PageImpl<>(List.of(), pageable, 0);

		when(referenciaExternaRepository.findAllByOrderByTituloAsc(pageable)).thenReturn(pageVazia);

		Page<ReferenciaExternaReponseDTO> resultado = referenciaExternaService.listar(pageable);

		assertThat(resultado.getContent()).isEmpty();
		assertThat(resultado.getTotalElements()).isZero();

		verify(referenciaExternaRepository).findAllByOrderByTituloAsc(pageable);
		verifyNoInteractions(referenciaExternaMapper);
	}

	@Test
	@DisplayName("Deve lançar uma exceção quando ocorrer erro ao buscar referências")
	void deveLancarExcecaoQuandoOcorrerErroAoBuscarReferencias() {
		Pageable pageable = PageRequest.of(0, 10);

		when(referenciaExternaRepository.findAllByOrderByTituloAsc(pageable))
				.thenThrow(new RuntimeException("Falha ao acessar banco"));

		assertThatThrownBy(() -> referenciaExternaService.listar(pageable))
				.isInstanceOf(RuntimeException.class);

		verify(referenciaExternaRepository).findAllByOrderByTituloAsc(pageable);
		verifyNoInteractions(referenciaExternaMapper);
	}

}