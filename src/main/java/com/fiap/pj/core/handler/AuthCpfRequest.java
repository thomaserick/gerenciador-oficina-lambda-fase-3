package com.fiap.pj.core.handler;

public record AuthCpfRequest(String cpf) {

    public record AuthCpfEvent(AuthCpfRequest body) {
    }
}


