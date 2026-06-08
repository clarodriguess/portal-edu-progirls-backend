package br.com.progirls.api.portal.repository;

import br.com.progirls.api.portal.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
    boolean existsByNomeUsuario(String nomeUsuario);
    Optional<Usuario> findByEmail(String email);
}
