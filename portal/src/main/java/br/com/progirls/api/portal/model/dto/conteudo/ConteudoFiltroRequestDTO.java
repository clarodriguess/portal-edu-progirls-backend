package br.com.progirls.api.portal.model.dto.conteudo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record ConteudoFiltroRequestDTO(

        List<Long> areasIds,
        List<Long> categoriasIds,
        List<Long> tecnologiasIds,
        List<Long> tagsIds,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataInicio,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataFim
) {

    public boolean hasAnyFilter() {
        return (areasIds != null && !areasIds.isEmpty())
                || (categoriasIds != null && !categoriasIds.isEmpty())
                || (tecnologiasIds != null && !tecnologiasIds.isEmpty())
                || (tagsIds != null && !tagsIds.isEmpty())
                || dataInicio != null
                || dataFim != null;
    }
}