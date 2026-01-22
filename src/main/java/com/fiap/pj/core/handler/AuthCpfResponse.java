package com.fiap.pj.core.handler;

public record AuthCpfResponse(Integer statusCode, AuthCpfResponseBody body) {

    public static AuthCpfResponse from(String token) {
        return new AuthCpfResponse(200, new AuthCpfResponseBody(token));
    }

    public record AuthCpfResponseBody(String token) {
    }
}


