package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.RoadmapMapper;
import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapDetalheResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapFiltroRequestDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapResponseDTO;
import br.com.progirls.api.portal.model.entity.NivelRoadmap;
import br.com.progirls.api.portal.model.entity.Roadmap;
import br.com.progirls.api.portal.repository.RoadmapRepository;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoadmapServiceTest {

    @Mock
    private RoadmapRepository roadmapRepository;

    @Mock
    private RoadmapMapper roadmapMapper;

    @InjectMocks
    private RoadmapService roadmapService;

    private Roadmap roadmap;
    private RoadmapResponseDTO roadmapResponseDTO;

    @BeforeEach
    void setUp() {
        roadmap = new Roadmap();
        roadmap.setId(1L);
        roadmap.setTitulo("Trilha Backend Java");
        roadmap.setDescricao("Caminho para se tornar dev backend");
        roadmap.setNivel(NivelRoadmap.INICIANTE);

        roadmapResponseDTO = new RoadmapResponseDTO(
                roadmap.getId(),
                roadmap.getTitulo(),
                roadmap.getDescricao(),
                roadmap.getNivel()
        );
    }

    @Test
    @DisplayName("Deve retornar página de roadmaps sem filtros")
    void deveRetornarPaginaDeRoadmapsSemFiltro() {
        Pageable pageable = PageRequest.of(0, 12);
        Page<Roadmap> page = new PageImpl<>(List.of(roadmap), pageable, 1);

        when(roadmapRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(roadmapMapper.toDTO(roadmap)).thenReturn(roadmapResponseDTO);

        PageResponseDTO<RoadmapResponseDTO> resultado = roadmapService.listarRoadmaps(0, 12, null);

        assertThat(resultado.getContent()).hasSize(1).containsExactly(roadmapResponseDTO);
        assertThat(resultado.getTotalElements()).isEqualTo(1);

        verify(roadmapRepository).findAll(any(Specification.class), eq(pageable));
        verify(roadmapMapper).toDTO(roadmap);
    }

    @Test
    @DisplayName("Deve retornar página de roadmaps filtrada por nível")
    void deveRetornarPaginaDeRoadmapsFiltradaPorNivel() {
        Pageable pageable = PageRequest.of(0, 12);
        Page<Roadmap> page = new PageImpl<>(List.of(roadmap), pageable, 1);
        RoadmapFiltroRequestDTO filtro = new RoadmapFiltroRequestDTO(null, null, NivelRoadmap.INICIANTE, null);

        when(roadmapRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(roadmapMapper.toDTO(roadmap)).thenReturn(roadmapResponseDTO);

        PageResponseDTO<RoadmapResponseDTO> resultado = roadmapService.listarRoadmaps(0, 12, filtro);

        assertThat(resultado.getContent()).hasSize(1).containsExactly(roadmapResponseDTO);

        verify(roadmapRepository).findAll(any(Specification.class), eq(pageable));
        verify(roadmapMapper).toDTO(roadmap);
    }

    @Test
    @DisplayName("Deve retornar página de roadmaps filtrada por título")
    void deveRetornarPaginaDeRoadmapsFiltradaPorTitulo() {
        Pageable pageable = PageRequest.of(0, 12);
        Page<Roadmap> page = new PageImpl<>(List.of(roadmap), pageable, 1);
        RoadmapFiltroRequestDTO filtro = new RoadmapFiltroRequestDTO("java", null, null, null);

        when(roadmapRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(roadmapMapper.toDTO(roadmap)).thenReturn(roadmapResponseDTO);

        PageResponseDTO<RoadmapResponseDTO> resultado = roadmapService.listarRoadmaps(0, 12, filtro);

        assertThat(resultado.getContent()).hasSize(1).containsExactly(roadmapResponseDTO);

        verify(roadmapRepository).findAll(any(Specification.class), eq(pageable));
        verify(roadmapMapper).toDTO(roadmap);
    }

    @Test
    @DisplayName("Deve retornar página vazia quando não houver roadmaps")
    void deveRetornarPaginaVaziaQuandoNaoHouverRoadmaps() {
        Pageable pageable = PageRequest.of(0, 12);
        Page<Roadmap> pageVazia = new PageImpl<>(List.of(), pageable, 0);

        when(roadmapRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(pageVazia);

        PageResponseDTO<RoadmapResponseDTO> resultado = roadmapService.listarRoadmaps(0, 12, null);

        assertThat(resultado.getContent()).isEmpty();
        assertThat(resultado.getTotalElements()).isZero();

        verify(roadmapRepository).findAll(any(Specification.class), eq(pageable));
        verifyNoInteractions(roadmapMapper);
    }

    @Test
    @DisplayName("Deve respeitar os parâmetros de paginação")
    void deveRespeiarParametrosDePaginacao() {
        Pageable pageable = PageRequest.of(1, 5);
        Page<Roadmap> page = new PageImpl<>(List.of(roadmap), pageable, 10);

        when(roadmapRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(roadmapMapper.toDTO(roadmap)).thenReturn(roadmapResponseDTO);

        PageResponseDTO<RoadmapResponseDTO> resultado = roadmapService.listarRoadmaps(1, 5, null);

        assertThat(resultado.getCurrentPage()).isEqualTo(1);
        assertThat(resultado.getSize()).isEqualTo(5);
        assertThat(resultado.getTotalElements()).isEqualTo(10);
        assertThat(resultado.getTotalPages()).isEqualTo(2);
    }

    @Test
    @DisplayName("Deve retornar detalhe do roadmap quando encontrado")
    void deveRetornarDetalheDoRoadmapQuandoEncontrado() {
        RoadmapDetalheResponseDTO detalheDTO = new RoadmapDetalheResponseDTO(
                roadmap.getId(), roadmap.getTitulo(), roadmap.getDescricao(), roadmap.getNivel(), List.of()
        );

        when(roadmapRepository.findById(1L)).thenReturn(Optional.of(roadmap));
        when(roadmapMapper.toDetalheDTO(roadmap)).thenReturn(detalheDTO);

        RoadmapDetalheResponseDTO resultado = roadmapService.buscarRoadmapPorId(1L);

        assertThat(resultado.id()).isEqualTo(1L);
        assertThat(resultado.titulo()).isEqualTo("Trilha Backend Java");
        assertThat(resultado.nivel()).isEqualTo(NivelRoadmap.INICIANTE);
        assertThat(resultado.conteudos()).isEmpty();

        verify(roadmapRepository).findById(1L);
        verify(roadmapMapper).toDetalheDTO(roadmap);
    }

    @Test
    @DisplayName("Deve lançar 404 quando roadmap não encontrado")
    void deveLancar404QuandoRoadmapNaoEncontrado() {
        when(roadmapRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roadmapService.buscarRoadmapPorId(99L))
                .isInstanceOf(ResponseStatusException.class);

        verify(roadmapRepository).findById(99L);
        verifyNoInteractions(roadmapMapper);
    }
}