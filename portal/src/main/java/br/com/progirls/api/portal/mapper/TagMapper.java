package br.com.progirls.api.portal.mapper;

import br.com.progirls.api.portal.model.dto.tag.TagResponseDTO;
import br.com.progirls.api.portal.model.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponseDTO toDTO(Tag tag);
}
