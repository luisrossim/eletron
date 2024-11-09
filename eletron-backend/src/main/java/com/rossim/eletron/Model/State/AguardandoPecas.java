package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.StatusServicoEnum;
import com.rossim.eletron.Model.Servico;
import com.rossim.eletron.Model.StatusServico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("AGUARDANDO_PECAS")
public class AguardandoPecas extends StatusServico {

    public AguardandoPecas() {
        this.setId(StatusServicoEnum.AGUARDANDO_PECAS.getId());
        this.setDescricao(StatusServicoEnum.AGUARDANDO_PECAS.getDescricao());
    }

    @Override
    public StatusServico next(Servico current) {
        return new EmExecucao();
    }

    @Override
    public StatusServico previous(Servico current) {
        return this;
    }

    @Override
    public boolean allowCancelar() {
        return true;
    }
}
