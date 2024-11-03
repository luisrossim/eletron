package com.rossim.eletron.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaDTO (
        Long id,
        ReformadoDTO reformado,
        ClienteDTO cliente,
        PagamentoFormaDTO pagamentoForma,
        BigDecimal valor,
        LocalDateTime efetuadaEm
) { }