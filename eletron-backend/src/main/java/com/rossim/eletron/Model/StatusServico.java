package com.rossim.eletron.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rossim.eletron.Deserializer.StatusServicoDeserializer;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "status_servico")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonDeserialize(using = StatusServicoDeserializer.class)
public abstract class StatusServico implements Serializable {
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


    public abstract StatusServico next(Servico servico);
    public abstract StatusServico previous(Servico servico);
}

