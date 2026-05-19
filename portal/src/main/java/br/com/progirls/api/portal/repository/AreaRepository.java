package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findAllByOrderByNomeAsc();
}
