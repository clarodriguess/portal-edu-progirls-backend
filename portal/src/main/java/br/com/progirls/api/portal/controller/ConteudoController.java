package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.service.ConteudoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conteudos")
@Tag(name = "Conteúdos", description = "Endpoints relacionados a conteúdos")
public class ConteudoController {

    private final ConteudoService conteudoService;

    public ConteudoController(ConteudoService conteudoService) {
        this.conteudoService = conteudoService;
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<ConteudoResponseDTO>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConteudoResponseDTO> conteudos = conteudoService.listar(pageable);
        PageResponseDTO<ConteudoResponseDTO> resultado = new PageResponseDTO<>(conteudos);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
