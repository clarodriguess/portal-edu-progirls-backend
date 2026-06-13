package br.com.progirls.api.portal.model.dto.roadmap;

import br.com.progirls.api.portal.model.entity.NivelRoadmap;
import java.util.List;

public record RoadmapDetalheResponseDTO(
        Long id,
        String titulo,
        String descricao,
        NivelRoadmap nivel,
        List<RoadmapConteudoResponseDTO> conteudos
) {}