package br.com.progirls.api.portal.service;

import br.com.progirls.api.portal.mapper.TagMapper;
import br.com.progirls.api.portal.model.dto.tag.TagResponseDTO;
import br.com.progirls.api.portal.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagResponseDTO> listar() {
        return tagRepository.findAllTagsSorted()
                .stream()
                .map(tagMapper::toDTO)
                .toList();
    }
}
