package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.referencia.ReferenciaExternaReponseDTO;
import br.com.progirls.api.portal.model.entity.ReferenciaExterna;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReferenciaExternaMapper {
    ReferenciaExternaReponseDTO toDTO(ReferenciaExterna referenciaExterna);
}
