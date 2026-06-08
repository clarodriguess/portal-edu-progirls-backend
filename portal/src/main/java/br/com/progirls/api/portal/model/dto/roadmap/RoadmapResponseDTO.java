package br.com.progirls.api.portal.model.dto.roadmap;

import br.com.progirls.api.portal.model.entity.NivelRoadmap;

public record RoadmapResponseDTO(
        Long id,
        String titulo,
        String descricao,
        NivelRoadmap nivel
) {
}
