package com.marcos.geradorrelatorioto.infrastructure.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String mensagem) {

        super(mensagem);
    }

    public AccessDeniedException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
