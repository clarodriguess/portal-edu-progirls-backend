package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.area.AreaResponseDTO;
import br.com.progirls.api.portal.model.entity.Area;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    AreaResponseDTO toDTO(Area area);
}
