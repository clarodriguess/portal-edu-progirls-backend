package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.model.dto.conteudo.ListaConteudosResponseDTO;
import br.com.progirls.api.portal.service.ConteudoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/conteudos")
@Tag(name = "Conteúdos", description = "Endpoints relacionados a conteúdos")
public class ConteudoController {

    private final ConteudoService conteudoService;

    public ConteudoController(ConteudoService conteudoService) {
        this.conteudoService = conteudoService;
    }

    @GetMapping
    public ResponseEntity<?> buscarConteudosComFiltros(
            @Parameter(description = "Área do conteúdo (ex: Frontend, Backend, Mobile, ...) - opcional")
            @RequestParam(required = false) String areaId,

            @Parameter(description = "Categoria do conteúdo (ex: Artigo, Vídeo, Curso, ...) - opcional")
            @RequestParam(required = false) String categoriaId,

            @Parameter(description = "Tecnologia relacionada ao conteúdo (ex: Java, React, Python, ...) - opcional")
            @RequestParam(required = false) List<String> tecnologiasIds,

            @Parameter(description = "Tag relacionada ao conteúdo (ex: Iniciante, Avançado, Dica, ...) - opcional")
            @RequestParam(required = false) List<String> tagsIds,

            @Parameter(description = "Data de início no formato ISO (YYYY-MM-DD)")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,

            @Parameter(description = "Data de fim no formato ISO (YYYY-MM-DD)")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ){

        Boolean comFiltro = areaId != null || categoriaId != null || (tecnologiasIds != null && !tecnologiasIds.isEmpty())
                || (tagsIds != null && !tagsIds.isEmpty()) || dataInicio != null || dataFim != null;

        if(comFiltro) {
            List<ConteudoResponseDTO> conteudos = conteudoService.buscarConteudosComFiltros(areaId, categoriaId, tecnologiasIds, tagsIds,dataInicio, dataFim);

            String mensagem = conteudos.isEmpty() ? "Nenhum conteúdo encontrado com os filtros aplicados."
                    : "Conteúdos encontrados com sucesso.";

            ListaConteudosResponseDTO resposta = new ListaConteudosResponseDTO(mensagem, conteudos);

            return new ResponseEntity<>(resposta, HttpStatus.OK);

        } else {
            Pageable pageable = PageRequest.of(0, 12);
            Page<ConteudoResponseDTO> conteudoPage = conteudoService.listarConteudosPaginados(pageable);
            PageResponseDTO<ConteudoResponseDTO> resposta = new PageResponseDTO<>(conteudoPage);
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        }
    }
}
