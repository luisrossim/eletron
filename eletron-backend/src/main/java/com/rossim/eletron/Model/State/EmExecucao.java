package com.rossim.eletron.Model.State;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rossim.eletron.Enum.StatusServicoEnum;
import com.rossim.eletron.Model.Servico;
import com.rossim.eletron.Model.StatusServico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EM_EXECUCAO")
public class EmExecucao extends StatusServico {

    public EmExecucao() { this.setId(StatusServicoEnum.EM_EXECUCAO.getId()); }

    @Override
    public StatusServico next(Servico current) {
        return new Finalizado();
    }

    @Override
    public StatusServico previous(Servico current) {
        return new Recebido();
    }

    @Override
    public boolean allowCancelar() {
        return true;
    }

    @Override
    public boolean allowVender() {
        return true;
    }

    @Override
    public boolean allowAguardandoPecas() {
        return true;
    }
}
