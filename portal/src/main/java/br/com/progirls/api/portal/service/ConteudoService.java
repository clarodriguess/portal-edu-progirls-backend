package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.ConteudoMapper;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.model.entity.ConteudoSpecification;
import br.com.progirls.api.portal.repository.ConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConteudoService {

    private final ConteudoRepository conteudoRepository;
    private final ConteudoMapper conteudoMapper;

    public List<ConteudoResponseDTO> buscarConteudosComFiltros (
                String areaId,
                String categoriaId,
                List<String> tecnologiasIds,
                List<String> tagsIds,
                LocalDate dataInicio,
                LocalDate dataFim
    ) {
        return conteudoRepository
                .findAll(ConteudoSpecification.comFiltros(areaId, categoriaId, tecnologiasIds, tagsIds, dataInicio, dataFim))
                .stream()
                .map(conteudoMapper::toDto)
                .toList();
    }

    public Page<ConteudoResponseDTO> listarConteudosPaginados(Pageable pageable) {
        return conteudoRepository.findAll(pageable)
                .map(conteudoMapper::toDto);
    }
}
