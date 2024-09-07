package com.armamentobelico.gerenciador_de_vendas.exceptions;

public class VendaNotFoundException extends RuntimeException {
    public VendaNotFoundException(String message) {
        super(message);
    }
}
