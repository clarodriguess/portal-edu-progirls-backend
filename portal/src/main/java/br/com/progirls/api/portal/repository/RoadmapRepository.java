package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.NivelRoadmap;
import br.com.progirls.api.portal.model.entity.Roadmap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long>, JpaSpecificationExecutor<Roadmap> {
    Page<Roadmap> findByNivel(NivelRoadmap nivel, Pageable pageable);
}