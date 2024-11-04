package com.rossim.eletron.Enum;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusServicoEnum {

    RECEBIDO(1L, "Recebido - O pedido foi recebido e está na fila", "RECEBIDO"),
    AGUARDANDO_PECAS(2L, "Aguardando Peças - O serviço está suspenso aguardando a chegada de peças", "AGUARDANDO_PECAS"),
    EM_EXECUCAO(3L, "Em Execução - O serviço está em andamento", "EM_EXECUCAO"),
    FINALIZADO(4L, "Finalizado - O serviço foi concluído", "FINALIZADO"),
    CANCELADO(5L, "Cancelado - O serviço foi cancelado", "CANCELADO"),
    VENDIDO(6L, "Vendido - O aparelho foi vendido pelo cliente para o estabelecimento", "VENDIDO");

    private Long id;
    private String descricao;
    private String tipo;
}
