package com.rossim.eletron.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "historico_servico")
public class HistoricoServico implements Serializable {

    public HistoricoServico() {
        this.id = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name="status_servico_id")
    private StatusServico status;
}
