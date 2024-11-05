package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.StatusServicoEnum;
import com.rossim.eletron.Model.Servico;
import com.rossim.eletron.Model.StatusServico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FINALIZADO")
public class Finalizado extends StatusServico {

    public Finalizado() {
        this.setId(StatusServicoEnum.FINALIZADO.getId());
        this.setDescricao(StatusServicoEnum.FINALIZADO.getDescricao());
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
