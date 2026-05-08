package br.com.progirls.api.portal.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "membro_equipe")
@Getter
@Setter
@NoArgsConstructor
public class MembroEquipe extends EntidadeBase {
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cargo;
    @Column(name = "foto_url", nullable = false)
    private String fotoUrl;
    @Column(name = "linkedin_url", nullable = false)
    private String linkedinUrl;
    @Column(name = "github_url", nullable = false)
    private String githubUrl;
}
