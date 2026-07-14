package br.com.progirls.api.portal.model.dto.roadmap;

import br.com.progirls.api.portal.model.entity.NivelRoadmap;

import java.util.List;

public record RoadmapFiltroRequestDTO(
        String titulo,
        String descricao,
        NivelRoadmap nivel,
        List<Long> tecnologiasIds
) {
    public boolean hasAnyFilter() {
        return (titulo != null && !titulo.isBlank())
                || (descricao != null && !descricao.isBlank())
                || nivel != null
                || (tecnologiasIds != null && !tecnologiasIds.isEmpty());
    }
}