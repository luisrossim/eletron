package com.rossim.eletron.DTO;

public record ChangeStatusDTO (
    Long servicoId,
    String action,
    String comments
){ }
