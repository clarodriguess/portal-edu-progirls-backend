package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.ReferenciaExterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenciaExternaRepository extends JpaRepository<ReferenciaExterna, Long> {
    List<ReferenciaExterna> findAllByOrderByTituloAsc();
}
