package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.ConteudoMapper;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.repository.ConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConteudoService {

    private final ConteudoRepository conteudoRepository;
    private final ConteudoMapper conteudoMapper;

    public Page<ConteudoResponseDTO> listar(Pageable pageable) {
        return conteudoRepository.findAll(pageable)
                .map(conteudoMapper::toDto);
    }
}
