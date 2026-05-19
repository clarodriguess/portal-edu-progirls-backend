package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.tecnologia.TecnologiaResponseDTO;
import br.com.progirls.api.portal.model.entity.Tecnologia;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface TecnologiaMapper {
    TecnologiaResponseDTO toDTO (Tecnologia tecnologia);
}
