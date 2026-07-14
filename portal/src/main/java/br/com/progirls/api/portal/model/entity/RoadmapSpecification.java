package br.com.progirls.api.portal.model.entity;

import br.com.progirls.api.portal.model.dto.roadmap.RoadmapFiltroRequestDTO;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RoadmapSpecification {

    public static Specification<Roadmap> comFiltros(RoadmapFiltroRequestDTO filtros) {

        if (filtros == null || !filtros.hasAnyFilter()) {
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (query != null) {
                query.distinct(true);
            }

            if (filtros.titulo() != null && !filtros.titulo().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("titulo")),
                                "%" + filtros.titulo().toLowerCase() + "%"
                        )
                );
            }

            if (filtros.descricao() != null && !filtros.descricao().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("descricao")),
                                "%" + filtros.descricao().toLowerCase() + "%"
                        )
                );
            }

            if (filtros.nivel() != null) {
                predicates.add(
                        cb.equal(root.get("nivel"), filtros.nivel())
                );
            }

            if (filtros.tecnologiasIds() != null && !filtros.tecnologiasIds().isEmpty()) {
                Join<Roadmap, Tecnologia> tecnologiaJoin = root.join("tecnologias");
                predicates.add(
                        tecnologiaJoin.get("id").in(filtros.tecnologiasIds())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}