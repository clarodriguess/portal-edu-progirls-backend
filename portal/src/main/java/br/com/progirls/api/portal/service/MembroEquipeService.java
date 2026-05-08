package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.MembroEquipeMapper;
import br.com.progirls.api.portal.model.dto.equipe.MembroEquipeResponseDTO;
import br.com.progirls.api.portal.repository.MembroEquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembroEquipeService {

    private final MembroEquipeRepository membroEquipeRepository;
    private final MembroEquipeMapper membroEquipeMapper;

    public List<MembroEquipeResponseDTO> listar() {
        return membroEquipeRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(membroEquipeMapper::toDTO)
                .toList();
    }
}
