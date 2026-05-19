package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.categoria.CategoriaResponseDTO;
import br.com.progirls.api.portal.model.entity.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaResponseDTO toDTO(Categoria categoria);
}