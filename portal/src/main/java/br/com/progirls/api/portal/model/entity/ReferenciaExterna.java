package br.com.progirls.api.portal.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "referencias_externas")
@Getter
@Setter
@NoArgsConstructor
public class ReferenciaExterna extends EntidadeBase {
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String link;
}
