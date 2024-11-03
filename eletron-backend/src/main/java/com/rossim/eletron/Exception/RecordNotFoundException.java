package com.rossim.eletron.Exception;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Nenhum registro encontrado com id: " + id);
    }
}