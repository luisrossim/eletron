package com.rossim.eletron.DTO;

import java.time.LocalDateTime;

public record ClienteDTO (
        Long id,
        String nome,
        String telefone,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) { }