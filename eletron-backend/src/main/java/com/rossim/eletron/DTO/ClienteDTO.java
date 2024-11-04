package com.rossim.eletron.DTO;

import java.time.LocalDateTime;

public record ClienteDTO (
        Long id,
        String nome,
        String telefone,
        boolean ativo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) { }