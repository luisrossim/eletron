package com.rossim.eletron.Model.State;

import com.rossim.eletron.Enum.EstadoServicoEnum;
import com.rossim.eletron.Model.EstadoServico;
import com.rossim.eletron.Model.Servico;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EM_EXECUCAO")
public class EmExecucao extends EstadoServico {

    public EmExecucao() {
        this.descricao = EstadoServicoEnum.EM_EXECUCAO.getDescricao();
    }

    @Override
    public EstadoServico next(Servico current) {
        return new Finalizado();
    }

    @Override
    public EstadoServico previous(Servico current) {
        return this;
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
