package com.fiap.pj.core.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pj.core.config.LambdaConfig;
import com.fiap.pj.core.usecase.AuthCpfUseCase;
import com.fiap.pj.core.usecase.command.AutenticarCommand;

import java.util.Map;

public class AuthCpfHandler
        implements RequestHandler<Map<String, Object>, AuthCpfResponse> {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final AuthCpfUseCase authCpfUseCase;

    public AuthCpfHandler() {
        this.authCpfUseCase = LambdaConfig.authCpfUseCase();
    }

    public AuthCpfHandler(AuthCpfUseCase authCpfUseCase) {
        this.authCpfUseCase = authCpfUseCase;
    }

    @Override
    public AuthCpfResponse handleRequest(
            Map<String, Object> event,
            Context context
    ) {
        Object body = event.get("body");
        AuthCpfRequest request;
        try {
            request = mapper.readValue((String) body, AuthCpfRequest.class);
        } catch (JsonProcessingException e) {
            return new AuthCpfResponse(404, null);
        }

        String token = authCpfUseCase.handle(new AutenticarCommand(request.cpf()));
        return AuthCpfResponse.from(token);
    }
}
