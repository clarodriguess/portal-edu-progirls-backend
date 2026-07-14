package br.com.progirls.api.portal.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roadmaps")
@Getter
@Setter
public class Roadmap extends EntidadeBase {
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelRoadmap nivel;

    @OneToMany(mappedBy = "roadmap", fetch = FetchType.LAZY)
    @OrderBy("ordem ASC")
    private List<RoadmapConteudo> conteudos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roadmap_tecnologia",
            joinColumns = @JoinColumn(name = "roadmap_id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id")
    )
    private List<Tecnologia> tecnologias;
}
