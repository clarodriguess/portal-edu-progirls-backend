package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.ErrorResponse;
import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoFiltroRequestDTO;
import br.com.progirls.api.portal.model.dto.conteudo.ConteudoResponseDTO;
import br.com.progirls.api.portal.service.ConteudoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Operation(summary = "Listar conteúdos", description = "Retorna uma lista paginada de conteúdos, com suporte a filtros opcionais")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista paginada de conteúdos retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetros inválidos (ex.: intervalo de datas inválido)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })

    @GetMapping
    public ResponseEntity<PageResponseDTO<ConteudoResponseDTO>> buscarConteudosComFiltros(
            @Parameter(description = "Número da página (padrão: 0)")
            @RequestParam(required = false, defaultValue = "0") int page,

            @Parameter(description = "Tamanho da página (padrão: 12)")
            @RequestParam(required = false, defaultValue = "12") int size,

            @Parameter(description = "Filtros opcionais (areaId, categoriaId, tecnologiasIds, tagsIds, dataInicio, dataFim)")
            @ParameterObject @ModelAttribute ConteudoFiltroRequestDTO filtro
    ){
        final int maxPageSize = 100;
        if (page < 0) page = 0;
        if (size <= 0) size = 12;
        if (size > maxPageSize) size = maxPageSize;

        final boolean comFiltro = filtro != null && filtro.hasAnyFilter();

        Pageable pageable = PageRequest.of(page, size);

        Page<ConteudoResponseDTO> conteudoPage = comFiltro
                ? conteudoService.buscarConteudosComFiltros(
                pageable,
                filtro)
                : conteudoService.listarConteudosPaginados(pageable);

        PageResponseDTO<ConteudoResponseDTO> resposta = new PageResponseDTO<>(conteudoPage);
        return ResponseEntity.ok(resposta);
    }
}
