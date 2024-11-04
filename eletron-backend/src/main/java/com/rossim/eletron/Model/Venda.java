package com.rossim.eletron.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reformado_id", unique = true, nullable = false)
    private Reformado reformado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "pagamento_forma_id", nullable = false)
    private PagamentoForma pagamentoForma;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "efetuada_em", nullable = false)
    private LocalDateTime efetuadaEm;


    @PrePersist
    protected void onCreate() {
        this.efetuadaEm = LocalDateTime.now();
    }
}

