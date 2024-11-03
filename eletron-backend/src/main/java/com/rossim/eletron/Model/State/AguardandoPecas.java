package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.EstadoServicoEnum;
import com.rossim.eletron.Model.EstadoServico;
import com.rossim.eletron.Model.Servico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("AGUARDANDO_PECAS")
public class AguardandoPecas extends EstadoServico {

    public AguardandoPecas() {
        this.descricao = EstadoServicoEnum.AGUARDANDO_PECAS.getDescricao();
    }

    @Override
    public EstadoServico next(Servico current) {
        return new EmExecucao();
    }

    @Override
    public EstadoServico previous(Servico current) {
        return this;
    }

    @Override
    public boolean allowCancelar() {
        return true;
    }
}
