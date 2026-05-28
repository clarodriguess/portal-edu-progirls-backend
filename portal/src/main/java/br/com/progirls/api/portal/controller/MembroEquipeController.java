package br.com.progirls.api.portal.controller;

import br.com.progirls.api.portal.model.dto.ErrorResponse;
import br.com.progirls.api.portal.model.dto.equipe.MembroEquipeResponseDTO;
import br.com.progirls.api.portal.service.MembroEquipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipe")
@Tag(name = "Equipe", description = "Endpoints para gerenciamento da equipe do projeto")
public class MembroEquipeController {

    private final MembroEquipeService membroEquipeService;

    public MembroEquipeController(MembroEquipeService membroEquipeService) {
        this.membroEquipeService = membroEquipeService;
    }

    @Operation(summary = "Listar membros da equipe", description = "Retorna todos os membros da equipe cadastrados ordenados por nome")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de membros da equipe retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MembroEquipeResponseDTO.class)
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
    public ResponseEntity<List<MembroEquipeResponseDTO>> listar() {
        List<MembroEquipeResponseDTO> membros = membroEquipeService.listar();
        return new ResponseEntity<>(membros, HttpStatus.OK);
    }
}
