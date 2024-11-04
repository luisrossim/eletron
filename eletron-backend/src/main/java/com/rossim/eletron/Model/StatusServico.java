package com.rossim.eletron.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rossim.eletron.Model.State.*;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "status_servico")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Recebido.class, name = "RECEBIDO"),
        @JsonSubTypes.Type(value = AguardandoPecas.class, name = "AGUARDANDO_PECAS"),
        @JsonSubTypes.Type(value = EmExecucao.class, name = "EM_EXECUCAO"),
        @JsonSubTypes.Type(value = Finalizado.class, name = "FINALIZADO"),
        @JsonSubTypes.Type(value = Vendido.class, name = "VENDIDO"),
        @JsonSubTypes.Type(value = Cancelado.class, name = "CANCELADO")
})
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

