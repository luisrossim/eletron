package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.StatusServicoEnum;
import com.rossim.eletron.Model.Servico;
import com.rossim.eletron.Model.StatusServico;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CANCELADO")
public class Cancelado extends StatusServico {

    public Cancelado() {
        this.setId(StatusServicoEnum.CANCELADO.getId());
        this.setDescricao(StatusServicoEnum.CANCELADO.getDescricao());
    }

    @Override
    public StatusServico next(Servico current) {
        return this;
    }

    @Override
    public StatusServico previous(Servico current) {
        return new EmExecucao();
    }
}