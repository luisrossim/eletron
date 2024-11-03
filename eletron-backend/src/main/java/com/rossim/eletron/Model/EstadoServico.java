package com.rossim.eletron.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "estado_servico")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class EstadoServico implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    protected String descricao;


    public boolean allowCancelar() {
        return false;
    }

    public boolean allowVender() {
        return false;
    }

    public boolean allowAguardandoPecas() {
        return false;
    }


    public abstract EstadoServico next(Servico servico);
    public abstract EstadoServico previous(Servico servico);
}

