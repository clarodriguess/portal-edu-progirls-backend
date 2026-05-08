package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.MembroEquipe;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroEquipeRepository extends JpaAttributeConverter<MembroEquipe, Long> {
    List<MembroEquipe> findAllByOrderByNomeAsc();
}
