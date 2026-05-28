package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.MembroEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroEquipeRepository extends JpaRepository<MembroEquipe, Long> {
    List<MembroEquipe> findAllByOrderByNomeAsc();
}
