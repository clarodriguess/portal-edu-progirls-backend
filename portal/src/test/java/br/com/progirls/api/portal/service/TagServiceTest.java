package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.TagMapper;
import br.com.progirls.api.portal.model.dto.tag.TagResponseDTO;
import br.com.progirls.api.portal.model.entity.Tag;
import br.com.progirls.api.portal.repository.TagRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

	@Mock
	private TagRepository tagRepository;

	@Mock
	private TagMapper tagMapper;

	@InjectMocks
	private TagService tagService;

	private Tag tag;
	private TagResponseDTO tagResponseDTO;

	@BeforeEach
	void setUp() {
		tag = new Tag();
		tag.setId(1L);
		tag.setNome("Iniciante");

		tagResponseDTO = new TagResponseDTO(tag.getId(), tag.getNome());
	}

	@Test
	@DisplayName("Deve retornar lista de tags quando existirem registros")
	void deveRetornarListaDeTagsQuandoExistiremRegistros() {
		when(tagRepository.findAllTagsSorted()).thenReturn(List.of(tag));
		when(tagMapper.toDTO(tag)).thenReturn(tagResponseDTO);

		List<TagResponseDTO> resultado = tagService.listar();

		assertThat(resultado)
				.hasSize(1)
				.containsExactly(tagResponseDTO);

		verify(tagRepository).findAllTagsSorted();
		verify(tagMapper).toDTO(tag);
	}

	@Test
	@DisplayName("Deve retornar lista vazia quando não houver registros")
	void deveRetornarListaVaziaQuandoNaoHouverRegistros() {
		when(tagRepository.findAllTagsSorted()).thenReturn(List.of());

		List<TagResponseDTO> resultado = tagService.listar();

		assertThat(resultado).isEmpty();

		verify(tagRepository).findAllTagsSorted();
		verifyNoInteractions(tagMapper);
	}

	@Test
	@DisplayName("Deve lançar exceção quando ocorrer erro ao buscar tags")
	void deveLancarExcecaoQuandoOcorrerErroAoBuscarTags() {
		when(tagRepository.findAllTagsSorted()).thenThrow(new RuntimeException("Falha ao acessar banco"));

		assertThatThrownBy(() -> tagService.listar()).isInstanceOf(RuntimeException.class);

		verify(tagRepository).findAllTagsSorted();
		verifyNoInteractions(tagMapper);
	}

}