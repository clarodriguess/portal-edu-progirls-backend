package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.AreaMapper;
import br.com.progirls.api.portal.model.dto.area.AreaResponseDTO;
import br.com.progirls.api.portal.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    @Cacheable("areas")
    public List<AreaResponseDTO> listar() {
        return areaRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(areaMapper::toDTO)
                .toList();
    }
}
