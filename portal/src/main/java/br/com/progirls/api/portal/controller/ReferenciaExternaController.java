package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.referencia.ReferenciaExternaReponseDTO;
import br.com.progirls.api.portal.service.ReferenciaExternaService;
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
@RequestMapping("/api/v1/referencias")
@Tag(name = "Referências Externas", description = "Endpoints para gerenciar referências externas")
public class ReferenciaExternaController {
    private final ReferenciaExternaService referenciaExternaService;

    public ReferenciaExternaController(ReferenciaExternaService referenciaExternaService) {
        this.referenciaExternaService = referenciaExternaService;
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<ReferenciaExternaReponseDTO>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReferenciaExternaReponseDTO> referenciaPage = referenciaExternaService.listar(pageable);
        PageResponseDTO<ReferenciaExternaReponseDTO> resultado = new PageResponseDTO<>(referenciaPage);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
