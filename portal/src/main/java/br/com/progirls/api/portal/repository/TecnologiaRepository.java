package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {

    List<Tecnologia> findAllByOrderByNomeAsc();
}
