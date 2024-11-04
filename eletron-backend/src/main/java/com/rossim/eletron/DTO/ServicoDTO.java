package com.rossim.eletron.DTO;

import com.rossim.eletron.Model.StatusServico;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record ServicoDTO (
        Long id,
        ClienteDTO cliente,
        TipoAparelhoDTO tipoAparelho,
        MarcaDTO marca,
        Set<DefeitoDTO> defeitos,
        String descricao,
        StatusServico status,
        BigDecimal valor,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,
        LocalDateTime finalizadoEm
) { }
