package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.ErrorResponse;
import br.com.progirls.api.portal.model.dto.PageResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapDetalheResponseDTO;
import br.com.progirls.api.portal.model.dto.roadmap.RoadmapResponseDTO;
import br.com.progirls.api.portal.model.entity.NivelRoadmap;
import br.com.progirls.api.portal.service.RoadmapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roadmaps")
@Tag(name = "Roadmaps", description = "Endpoints para listagem de roadmaps")
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @Operation(summary = "Listar roadmaps", description = "Retorna roadmaps paginados com filtro opcional por nível")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de roadmaps retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageResponseDTO.class)
                    )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro inválido (ex.: nível inexistente)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping
    public ResponseEntity<PageResponseDTO<RoadmapResponseDTO>> listarRoadmaps(
            @Parameter(description = "Número da página (padrão: 0)")
            @RequestParam(required = false, defaultValue = "0") int page,

            @Parameter(description = "Tamanho da página (padrão: 12)")
            @RequestParam(required = false, defaultValue = "12") int size,

            @Parameter(description = "Filtro por nível: INICIANTE, INTERMEDIARIO ou AVANCADO")
            @RequestParam(required = false) NivelRoadmap nivel
    ) {
        final int maxPageSize = 100;
        if (page < 0) page = 0;
        if (size <= 0) size = 12;
        if (size > maxPageSize) size = maxPageSize;

        return ResponseEntity.ok(roadmapService.listarRoadmaps(page, size, nivel));
    }

    @Operation(summary = "Detalhar roadmap", description = "Retorna os detalhes de um roadmap e seus conteúdos ordenados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roadmap retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoadmapDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Roadmap não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoadmapDetalheResponseDTO> buscarRoadmapPorId(
            @Parameter(description = "ID do roadmap") @PathVariable Long id
    ) {
        return ResponseEntity.ok(roadmapService.buscarRoadmapPorId(id));
    }
}