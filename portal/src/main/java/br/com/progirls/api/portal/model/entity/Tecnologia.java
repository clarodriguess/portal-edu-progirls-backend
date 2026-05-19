package br.com.progirls.api.portal.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tecnologia")
@Getter
@Setter
@NoArgsConstructor
public class Tecnologia extends EntidadeBase {

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "tecnologias", fetch = FetchType.LAZY)
    private List<Conteudo> conteudos;
}