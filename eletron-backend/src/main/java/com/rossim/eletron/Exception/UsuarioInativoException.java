package com.rossim.eletron.Exception;

public class UsuarioInativoException extends BusinessRuleException {
    private static final long serialVersionUID = 1L;

    public UsuarioInativoException(Long id) {
        super("Usuário com ID " + id + " está inativo e não pode realizar esta operação.");
    }
}