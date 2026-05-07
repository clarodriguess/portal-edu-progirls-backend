package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.ErrorResponse;
import br.com.progirls.api.portal.model.dto.tecnologia.TecnologiaResponseDTO;
import br.com.progirls.api.portal.service.TecnologiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tecnologias")
@Tag(name = "Tecnologias", description = "Endpoints para gerenciamento de tecnologias")

public class TecnologiaController {
    private final TecnologiaService tecnologiaService;

    public TecnologiaController(TecnologiaService tecnologiaService) {
        this.tecnologiaService = tecnologiaService;
    }

    @Operation(
            summary = "Listar tecnologias",
            description = "Retorna todas as tecnologias cadastradas ordenadas por nome"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de tecnologias retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TecnologiaResponseDTO.class)
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
    public ResponseEntity<List<TecnologiaResponseDTO>> listar() {
        return ResponseEntity.ok(tecnologiaService.listar());
    }
}
