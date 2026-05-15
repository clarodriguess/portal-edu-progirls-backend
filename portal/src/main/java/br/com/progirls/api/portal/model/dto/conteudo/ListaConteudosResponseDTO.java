package br.com.progirls.api.portal.model.dto.conteudo;

import java.util.List;

public record ListaConteudosResponseDTO(
        String mensagem,
        List<ConteudoResponseDTO> conteudos
) {
}
