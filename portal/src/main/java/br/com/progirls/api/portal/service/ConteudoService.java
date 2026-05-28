package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.ConteudoMapper;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoFiltroRequestDTO;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.model.entity.ConteudoSpecification;
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

    public Page<ConteudoResponseDTO> buscarConteudosComFiltros(
            Pageable pageable,
            ConteudoFiltroRequestDTO filtros
    ) {
        if (filtros != null
                && filtros.dataInicio() != null
                && filtros.dataFim() != null
                && filtros.dataInicio().isAfter(filtros.dataFim())) {
            throw new IllegalArgumentException("A data de início deve ser anterior ou igual à data de fim.");
        }

        return conteudoRepository
                .findAll(ConteudoSpecification.comFiltros(filtros), pageable)
                .map(conteudoMapper::toDto);
    }

    public Page<ConteudoResponseDTO> listarConteudosPaginados(Pageable pageable) {
        return conteudoRepository.findAll(pageable)
                .map(conteudoMapper::toDto);
    }
}
