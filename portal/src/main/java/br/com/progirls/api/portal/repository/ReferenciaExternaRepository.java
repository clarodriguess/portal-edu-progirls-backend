package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.ReferenciaExterna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenciaExternaRepository extends JpaRepository<ReferenciaExterna, Long> {
    Page<ReferenciaExterna> findAllByOrderByTituloAsc(Pageable pageable);
}
