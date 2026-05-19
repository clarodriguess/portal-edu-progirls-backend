package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.AreaMapper;
import br.com.progirls.api.portal.model.dto.area.AreaResponseDTO;
import br.com.progirls.api.portal.model.entity.Area;
import br.com.progirls.api.portal.repository.AreaRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaServiceTest {

    @Mock
    private AreaRepository areaRepository;

    @Mock
    private AreaMapper areaMapper;

    @InjectMocks
    private AreaService areaService;

    private Area area;
    private AreaResponseDTO areaResponseDTO;

    @BeforeEach
    void setUp() {
        area = new Area();
        area.setId(1L);
        area.setNome("Backend");

        areaResponseDTO = new AreaResponseDTO(area.getId(), area.getNome());
    }

    @Test
    @DisplayName("Deve retornar lista de áreas quando existirem áreas cadastradas")
    void deveRetornarListaDeAreasQuandoExistiremAreas() {
        when(areaRepository.findAllByOrderByNomeAsc()).thenReturn(List.of(area));
        when(areaMapper.toDTO(area)).thenReturn(areaResponseDTO);

        List<AreaResponseDTO> resultado = areaService.listar();

        assertThat(resultado)
                .hasSize(1)
                .containsExactly(areaResponseDTO);

        verify(areaRepository).findAllByOrderByNomeAsc();
        verify(areaMapper).toDTO(area);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem áreas")
    void deveRetornarListaVaziaQuandoNaoExistiremAreas() {
        when(areaRepository.findAllByOrderByNomeAsc()).thenReturn(List.of());

        List<AreaResponseDTO> resultado = areaService.listar();

        assertThat(resultado).isEmpty();

        verify(areaRepository).findAllByOrderByNomeAsc();
        verifyNoInteractions(areaMapper);
    }

    @Test
    @DisplayName("Deve lançar exceção quando ocorrer erro ao buscar áreas")
    void deveLancarExcecaoQuandoRepositoryFalhar() {
        when(areaRepository.findAllByOrderByNomeAsc()).thenThrow(new RuntimeException("Falha ao acessar banco"));

        assertThatThrownBy(() -> areaService.listar()).isInstanceOf(RuntimeException.class);

        verify(areaRepository).findAllByOrderByNomeAsc();
        verifyNoInteractions(areaMapper);
    }
}
