package br.com.progirls.api.portal.model.entity;

import br.com.progirls.api.portal.model.dto.conteudo.ConteudoFiltroRequestDTO;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConteudoSpecification {
    public static Specification<Conteudo> comFiltros(ConteudoFiltroRequestDTO filtros) {
        if (filtros == null) {
            return (root, query, cb) -> cb.conjunction();
        }

        final String areaId = filtros.areaId();
        final String categoriaId = filtros.categoriaId();
        final List<String> tecnologiasIds = filtros.tecnologiasIds();
        final List<String> tagsIds = filtros.tagsIds();
        final LocalDate dataInicio = filtros.dataInicio();
        final LocalDate dataFim = filtros.dataFim();

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (areaId != null) {
                predicates.add(cb.equal(root.get("area").get("id"), areaId));
            }

            if (categoriaId != null) {
                predicates.add(cb.equal(root.get("categoria").get("id"), categoriaId));
            }

            if (tecnologiasIds != null && !tecnologiasIds.isEmpty()) {
                Join<Conteudo, Tecnologia> tecnologiaJoin = root.join("tecnologias");
                predicates.add(tecnologiaJoin.get("id").in(tecnologiasIds));
                if (query != null) {
                    query.distinct(true);
                }
            }

            if (tagsIds != null && !tagsIds.isEmpty()) {
                Join<Conteudo, Tag> tagJoin = root.join("tags");
                predicates.add(tagJoin.get("id").in(tagsIds));
                if (query != null) {
                    query.distinct(true);
                }
            }

            if (dataInicio != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataPublicacao"), dataInicio));
            }

            if (dataFim != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataPublicacao"), dataFim));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
