package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.model.entity.Conteudo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConteudoMapper {
    ConteudoResponseDTO toDto(Conteudo conteudo);
}
