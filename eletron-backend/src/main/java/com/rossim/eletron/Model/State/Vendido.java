package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.StatusServicoEnum;
import com.rossim.eletron.Model.Servico;
import com.rossim.eletron.Model.StatusServico;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VENDIDO")
public class Vendido extends StatusServico {

    public Vendido() {
        this.setId(StatusServicoEnum.VENDIDO.getId());
        this.setDescricao(StatusServicoEnum.VENDIDO.getDescricao());
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
