package com.rossim.eletron.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "servico")
public class Servico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tipo_aparelho_id", nullable = false)
    private TipoAparelho tipoAparelho;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToMany
    @JoinTable(
            name = "servico_defeito",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "defeito_id")
    )
    private Set<Defeito> defeitos;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusServico status;

    private BigDecimal valor;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @Column(name = "finalizado_em")
    private LocalDateTime finalizadoEm;


    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.criadoEm = now;
        this.atualizadoEm = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
}

