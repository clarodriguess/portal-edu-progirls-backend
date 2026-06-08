package br.com.progirls.api.portal.security;

import br.com.progirls.api.portal.model.entity.Usuario;
import br.com.progirls.api.portal.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final UsuarioRepository usuarioRepository;

    public Usuario getUsuarioAutenticado(Authentication authentication) {
        String nomeUsuario = authentication.getName();
        return usuarioRepository.findByNomeUsuario(nomeUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ("Usuário não encontrado")));
    }

    public Long getIdUsuarioAutenticado(Authentication authentication) {
        return getUsuarioAutenticado(authentication).getId();
    }
}
