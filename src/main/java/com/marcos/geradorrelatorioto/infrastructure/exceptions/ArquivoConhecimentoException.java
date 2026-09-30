package com.marcos.geradorrelatorioto.infrastructure.exceptions;

public class ArquivoConhecimentoException extends RuntimeException {

    public ArquivoConhecimentoException(String mensagem) {

        super(mensagem);
    }

    public ArquivoConhecimentoException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
