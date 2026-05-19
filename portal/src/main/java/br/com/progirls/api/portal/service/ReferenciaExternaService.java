package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.ReferenciaExternaMapper;
import br.com.progirls.api.portal.model.dto.referencia.ReferenciaExternaReponseDTO;
import br.com.progirls.api.portal.repository.ReferenciaExternaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReferenciaExternaService {

    private final ReferenciaExternaRepository referenciaExternaRepository;
    private final ReferenciaExternaMapper referenciaExternaMapper;

    public Page<ReferenciaExternaReponseDTO> listar(Pageable pageable) {
        return referenciaExternaRepository.findAllByOrderByTituloAsc(pageable)
                .map(referenciaExternaMapper::toDTO);
    }
}
