package br.com.progirls.api.portal.model.entity;

import br.com.progirls.api.portal.model.dto.conteudo.ConteudoFiltroRequestDTO;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ConteudoSpecification {

    public static Specification<Conteudo> comFiltros(
            ConteudoFiltroRequestDTO filtros
    ) {

        if (filtros == null || !filtros.hasAnyFilter()) {
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (query != null) {
                query.distinct(true);
            }

            if (filtros.areasIds() != null && !filtros.areasIds().isEmpty()) {
                predicates.add(
                        root.get("area")
                                .get("id")
                                .in(filtros.areasIds())
                );
            }

            if (filtros.categoriasIds() != null && !filtros.categoriasIds().isEmpty()) {
                predicates.add(
                        root.get("categoria")
                                .get("id")
                                .in(filtros.categoriasIds())
                );
            }

            if (filtros.tecnologiasIds() != null && !filtros.tecnologiasIds().isEmpty()) {

                Join<Conteudo, Tecnologia> tecnologiaJoin =
                        root.join("tecnologias");

                predicates.add(
                        tecnologiaJoin.get("id")
                                .in(filtros.tecnologiasIds())
                );
            }

            if (filtros.tagsIds() != null && !filtros.tagsIds().isEmpty()) {

                Join<Conteudo, Tag> tagJoin =
                        root.join("tags");

                predicates.add(
                        tagJoin.get("id")
                                .in(filtros.tagsIds())
                );
            }

            if (filtros.dataInicio() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("dataPublicacao"),
                                filtros.dataInicio()
                        )
                );
            }

            if (filtros.dataFim() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("dataPublicacao"),
                                filtros.dataFim()
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}