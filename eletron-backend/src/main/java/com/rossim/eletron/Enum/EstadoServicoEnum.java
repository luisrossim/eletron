package com.rossim.eletron.Enum;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EstadoServicoEnum {

    RECEBIDO(1L, "Recebido - O pedido foi recebido e está na fila"),
    AGUARDANDO_PECAS(2L, "Aguardando Peças - O serviço está suspenso aguardando a chegada de peças"),
    EM_EXECUCAO(3L, "Em Execução - O serviço está em andamento"),
    FINALIZADO(4L, "Finalizado - O serviço foi concluído"),
    CANCELADO(5L, "Cancelado - O serviço foi cancelado"),
    VENDIDO(6L, "Vendido - O aparelho foi vendido pelo cliente para o estabelecimento");

    private Long id;
    private String descricao;
}
