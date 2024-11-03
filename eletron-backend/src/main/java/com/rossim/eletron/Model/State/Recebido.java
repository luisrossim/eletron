package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.EstadoServicoEnum;
import com.rossim.eletron.Model.EstadoServico;
import com.rossim.eletron.Model.Servico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RECEBIDO")
public class Recebido extends EstadoServico {

    public Recebido() {
        this.descricao = EstadoServicoEnum.RECEBIDO.getDescricao();
    }

    @Override
    public EstadoServico next(Servico current) {
        return new EmExecucao();
    }

    @Override
    public EstadoServico previous(Servico current) {
        return this;
    }
}
