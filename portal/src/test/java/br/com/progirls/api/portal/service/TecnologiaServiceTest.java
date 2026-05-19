package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.TecnologiaMapper;
import br.com.progirls.api.portal.model.dto.tecnologia.TecnologiaResponseDTO;
import br.com.progirls.api.portal.model.entity.Tecnologia;
import br.com.progirls.api.portal.repository.TecnologiaRepository;
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
class TecnologiaServiceTest {

    @Mock
    private TecnologiaRepository tecnologiaRepository;

    @Mock
    private TecnologiaMapper tecnologiaMapper;

    @InjectMocks
    private TecnologiaService tecnologiaService;

    private Tecnologia tecnologia;
    private TecnologiaResponseDTO tecnologiaResponseDTO;

    @BeforeEach
    void setUp() {

        tecnologia = new Tecnologia();
        tecnologia.setId(1L);
        tecnologia.setNome("Java");

        tecnologiaResponseDTO =
                new TecnologiaResponseDTO(
                        tecnologia.getId(),
                        tecnologia.getNome()
                );
    }

    @Test
    @DisplayName("Deve retornar lista de tecnologias quando existirem tecnologias cadastradas")
    void deveRetornarListaDeTecnologiasQuandoExistiremTecnologias() {

        when(tecnologiaRepository.findAllByOrderByNomeAsc())
                .thenReturn(List.of(tecnologia));

        when(tecnologiaMapper.toDTO(tecnologia))
                .thenReturn(tecnologiaResponseDTO);

        List<TecnologiaResponseDTO> resultado =
                tecnologiaService.listar();

        assertThat(resultado)
                .hasSize(1)
                .containsExactly(tecnologiaResponseDTO);

        verify(tecnologiaRepository)
                .findAllByOrderByNomeAsc();

        verify(tecnologiaMapper)
                .toDTO(tecnologia);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem tecnologias")
    void deveRetornarListaVaziaQuandoNaoExistiremTecnologias() {

        when(tecnologiaRepository.findAllByOrderByNomeAsc())
                .thenReturn(List.of());

        List<TecnologiaResponseDTO> resultado =
                tecnologiaService.listar();

        assertThat(resultado).isEmpty();

        verify(tecnologiaRepository)
                .findAllByOrderByNomeAsc();

        verifyNoInteractions(tecnologiaMapper);
    }

    @Test
    @DisplayName("Deve lançar exceção quando ocorrer erro ao buscar tecnologias")
    void deveLancarExcecaoQuandoRepositoryFalhar() {

        when(tecnologiaRepository.findAllByOrderByNomeAsc())
                .thenThrow(new RuntimeException("Falha ao acessar banco"));

        assertThatThrownBy(() -> tecnologiaService.listar())
                .isInstanceOf(RuntimeException.class);

        verify(tecnologiaRepository)
                .findAllByOrderByNomeAsc();

        verifyNoInteractions(tecnologiaMapper);
    }
}