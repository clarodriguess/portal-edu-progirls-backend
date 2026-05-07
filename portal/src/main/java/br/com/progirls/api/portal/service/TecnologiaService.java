package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.TecnologiaMapper;
import br.com.progirls.api.portal.model.dto.tecnologia.TecnologiaResponseDTO;
import br.com.progirls.api.portal.repository.TecnologiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnologiaService {

    private final TecnologiaRepository tecnologiaRepository;
    private final TecnologiaMapper tecnologiaMapper;

    @Cacheable("tecnologias")
    public List<TecnologiaResponseDTO> listar() {
        return tecnologiaRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(tecnologiaMapper::toDTO)
                .toList();
    }

}
