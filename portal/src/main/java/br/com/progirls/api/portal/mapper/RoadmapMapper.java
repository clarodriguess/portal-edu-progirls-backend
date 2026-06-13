package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResumoResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapConteudoResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapDetalheResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapResponseDTO;
import br.com.progirls.api.portal.model.entity.Conteudo;
import br.com.progirls.api.portal.model.entity.Roadmap;
import br.com.progirls.api.portal.model.entity.RoadmapConteudo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoadmapMapper {
    RoadmapResponseDTO toDTO(Roadmap roadmap);
    RoadmapDetalheResponseDTO toDetalheDTO(Roadmap roadmap);
    RoadmapConteudoResponseDTO toRoadmapConteudoDTO(RoadmapConteudo roadmapConteudo);
    ConteudoResumoResponseDTO toConteudoResumoDTO(Conteudo conteudo);
}
