package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c ORDER BY c.nome ASC")
    List<Categoria> findAllCategoriasSorted();
}
