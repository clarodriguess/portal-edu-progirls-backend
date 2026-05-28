package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.CategoriaMapper;
import br.com.progirls.api.portal.model.dto.categoria.CategoriaResponseDTO;
import br.com.progirls.api.portal.model.entity.Categoria;
import br.com.progirls.api.portal.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;
    private CategoriaResponseDTO categoriaResponseDTO;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Artigo");
        categoria.setDataCriacao(LocalDateTime.now());

        categoriaResponseDTO = new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }

    @Test
    @DisplayName("Deve retornar lista de categorias quando existirem registros")
    void deveRetornarListaDeCategoriasQuandoExistiremRegistros() {
        when(categoriaRepository.findAllCategoriasSorted()).thenReturn(List.of(categoria));
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaResponseDTO);

        List<CategoriaResponseDTO> categorias = categoriaService.listar();

        assertThat(categorias)
                .hasSize(1)
                .containsExactly(categoriaResponseDTO);

        verify(categoriaRepository).findAllCategoriasSorted();
        verify(categoriaMapper).toDTO(categoria);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver registros")
    void deveRetornarListaVaziaQuandoNaoHouverRegistros() {
        when(categoriaRepository.findAllCategoriasSorted()).thenReturn(List.of());

        List<CategoriaResponseDTO> categorias = categoriaService.listar();

        assertThat(categorias).isEmpty();

        verify(categoriaRepository).findAllCategoriasSorted();
        verifyNoInteractions(categoriaMapper);
    }

    @Test
    @DisplayName("Deve lançar exceção quando ocorrer erro ao buscar categorias")
    void deveLancarExcecaoQuandoOcorrerErroAoBuscarCategorias() {
        when(categoriaRepository.findAllCategoriasSorted()).thenThrow(new RuntimeException("Falha ao acessar banco"));

        assertThatThrownBy(() -> categoriaService.listar()).isInstanceOf(RuntimeException.class);

        verify(categoriaRepository).findAllCategoriasSorted();
        verifyNoInteractions(categoriaMapper);
    }
}