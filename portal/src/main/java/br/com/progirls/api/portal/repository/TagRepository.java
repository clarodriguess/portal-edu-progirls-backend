package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT t FROM Tag t ORDER BY t.nome ASC")
    List<Tag> findAllTagsSorted();
}
