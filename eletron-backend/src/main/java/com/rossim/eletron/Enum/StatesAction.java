package com.rossim.eletron.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatesAction {

    NEXT(1L, "next"),
    PREVIOUS(2L, "previous");

    private Long id;
    private String descricao;
}