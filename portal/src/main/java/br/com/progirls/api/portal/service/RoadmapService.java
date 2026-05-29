package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.RoadmapMapper;
import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapResponseDTO;
import br.com.progirls.api.portal.model.entity.NivelRoadmap;
import br.com.progirls.api.portal.repository.RoadmapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final RoadmapMapper roadmapMapper;

    public PageResponseDTO<RoadmapResponseDTO> listarRoadmaps(int page, int size, NivelRoadmap nivel) {
        Pageable pageable = PageRequest.of(page, size);

        var roadmaps = (nivel != null)
                ? roadmapRepository.findByNivel(nivel, pageable)
                : roadmapRepository.findAll(pageable);

        return new PageResponseDTO<>(roadmaps.map(roadmapMapper::toDTO));
    }
}
