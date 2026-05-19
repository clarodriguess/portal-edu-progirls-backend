package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.ErrorResponse;
import br.com.progirls.api.portal.model.dto.area.AreaResponseDTO;
import br.com.progirls.api.portal.service.AreaService;
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
@RequestMapping("/api/v1/areas")
@Tag(name = "Áreas", description = "Endpoints para gerenciamento de áreas")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @Operation(summary = "Listar áreas", description = "Retorna todas as áreas cadastradas ordenadas por nome")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de áreas retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AreaResponseDTO.class)
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
    public ResponseEntity<List<AreaResponseDTO>> listar() {
        return ResponseEntity.ok(areaService.listar());
    }
}
