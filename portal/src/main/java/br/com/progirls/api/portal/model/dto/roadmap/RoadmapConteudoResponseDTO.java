package br.com.progirls.api.portal.model.dto.roadmap;

import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResumoResponseDTO;

public record RoadmapConteudoResponseDTO(
        Integer ordem,
        ConteudoResumoResponseDTO conteudo
) {}