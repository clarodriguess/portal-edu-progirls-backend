package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.CategoriaMapper;
import br.com.progirls.api.portal.model.dto.categoria.CategoriaResponseDTO;
import br.com.progirls.api.portal.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAllCategoriasSorted()
                .stream()
                .map(categoriaMapper::toDTO)
                .toList();
    }
}
