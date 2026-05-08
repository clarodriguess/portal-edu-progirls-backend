package br.com.progirls.api.portal.model.dto.equipe;

public record MembroEquipeResponseDTO(
        Long id,
        String nome,
        String cargo,
        String fotoUrl,
        String linkedinUrl,
        String githubUrl
) {
}
