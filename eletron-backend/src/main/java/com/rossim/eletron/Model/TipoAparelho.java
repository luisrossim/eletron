package com.rossim.eletron.Model;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tipo_aparelho")
public class TipoAparelho implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;
}