package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.roadmap.RoadmapResponseDTO;
import br.com.progirls.api.portal.model.entity.Roadmap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoadmapMapper {
    RoadmapResponseDTO toDTO(Roadmap roadmap);
}
