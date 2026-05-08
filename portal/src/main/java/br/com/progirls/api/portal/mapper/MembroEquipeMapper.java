package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.equipe.MembroEquipeResponseDTO;
import br.com.progirls.api.portal.model.entity.MembroEquipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembroEquipeMapper {
    MembroEquipeResponseDTO toDTO(MembroEquipe membroEquipe);
}
