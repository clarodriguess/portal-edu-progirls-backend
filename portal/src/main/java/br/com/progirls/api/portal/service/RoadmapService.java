package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.RoadmapMapper;
import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapDetalheResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapFiltroRequestDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapResponseDTO;
import br.com.progirls.api.portal.model.entity.NivelRoadmap;
import br.com.progirls.api.portal.model.entity.RoadmapSpecification;
import br.com.progirls.api.portal.repository.RoadmapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final RoadmapMapper roadmapMapper;

    public PageResponseDTO<RoadmapResponseDTO> listarRoadmaps(int page, int size, RoadmapFiltroRequestDTO filtro) {
        Pageable pageable = PageRequest.of(page, size);

        var roadmaps = roadmapRepository.findAll(
                RoadmapSpecification.comFiltros(filtro),
                pageable
        );

        return new PageResponseDTO<>(roadmaps.map(roadmapMapper::toDTO));
    }

    public RoadmapDetalheResponseDTO buscarRoadmapPorId(Long id) {
        var roadmap = roadmapRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roadmap não encontrado"));

        return roadmapMapper.toDetalheDTO(roadmap);
    }
}
