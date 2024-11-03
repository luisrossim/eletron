package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.EstadoServicoEnum;
import com.rossim.eletron.Model.EstadoServico;
import com.rossim.eletron.Model.Servico;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CANCELADO")
public class Cancelado extends EstadoServico {

    public Cancelado() {
        this.descricao = EstadoServicoEnum.CANCELADO.getDescricao();
    }

    @Override
    public EstadoServico next(Servico current) {
        return this;
    }

    @Override
    public EstadoServico previous(Servico current) {
        return new EmExecucao();
    }
}