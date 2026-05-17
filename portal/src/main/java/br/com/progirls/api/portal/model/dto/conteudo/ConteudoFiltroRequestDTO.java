package br.com.progirls.api.portal.model.dto.conteudo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record ConteudoFiltroRequestDTO(
        String areaId,
        String categoriaId,
        List<String> tecnologiasIds,
        List<String> tagsIds,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataInicio,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataFim
) {

    public boolean hasAnyFilter() {
        return areaId != null
                || categoriaId != null
                || (tecnologiasIds != null && !tecnologiasIds.isEmpty())
                || (tagsIds != null && !tagsIds.isEmpty())
                || dataInicio != null
                || dataFim != null;
    }
}
