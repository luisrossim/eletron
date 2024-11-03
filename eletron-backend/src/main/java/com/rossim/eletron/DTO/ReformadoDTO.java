package com.rossim.eletron.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReformadoDTO (
        Long id,
        MarcaDTO marca,
        String modelo,
        String descricao,
        BigDecimal valor,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) { }
