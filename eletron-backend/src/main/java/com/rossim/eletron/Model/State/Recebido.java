package com.rossim.eletron.Model.State;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rossim.eletron.Enum.StatusServicoEnum;
import com.rossim.eletron.Model.Servico;
import com.rossim.eletron.Model.StatusServico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RECEBIDO")
public class Recebido extends StatusServico {

    public Recebido() { this.setId(StatusServicoEnum.RECEBIDO.getId()); }

    @Override
    public StatusServico next(Servico current) {
        return new EmExecucao();
    }

    @Override
    public StatusServico previous(Servico current) {
        return this;
    }
}
