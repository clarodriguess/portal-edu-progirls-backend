package br.com.progirls.api.portal.model.dto.conteudo;

import br.com.progirls.api.portal.model.dto.area.AreaResponseDTO;
import br.com.progirls.api.portal.model.dto.categoria.CategoriaResponseDTO;
import br.com.progirls.api.portal.model.dto.tag.TagResponseDTO;
import br.com.progirls.api.portal.model.dto.tecnologia.TecnologiaResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ConteudoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String link,
        String imagemUrl,
        LocalDateTime dataPublicacao,
        AreaResponseDTO area,
        CategoriaResponseDTO categoria,
        List<TecnologiaResponseDTO> tecnologias,
        List<TagResponseDTO> tags
) {
}
