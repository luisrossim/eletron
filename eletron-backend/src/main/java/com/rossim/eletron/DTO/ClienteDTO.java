package com.rossim.eletron.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ClienteDTO (
        Long id,
        String nome,
        String telefone,
        boolean ativo,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime atualizadoEm
) { }