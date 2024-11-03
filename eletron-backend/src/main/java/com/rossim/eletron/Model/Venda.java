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
    @JoinColumn(name = "reformado_id", nullable = false)
    private Reformado device;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente customer;

    @ManyToOne
    @JoinColumn(name = "pagamento_forma_id", nullable = false)
    private PagamentoForma pagamentoForma;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
}

