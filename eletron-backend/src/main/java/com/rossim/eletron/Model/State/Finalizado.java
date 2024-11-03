package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.EstadoServicoEnum;
import com.rossim.eletron.Model.EstadoServico;
import com.rossim.eletron.Model.Servico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FINALIZADO")
public class Finalizado extends EstadoServico {

    public Finalizado() {
        this.descricao = EstadoServicoEnum.FINALIZADO.getDescricao();
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
