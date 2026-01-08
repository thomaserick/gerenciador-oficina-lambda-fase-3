package com.fiap.pj.core;

import com.fiap.pj.core.app.AuthCpfService;
import com.fiap.pj.core.client.ClientesApiClient;
import com.fiap.pj.core.handler.AuthCpfHandler;
import com.fiap.pj.core.handler.AuthCpfResponse;
import com.fiap.pj.core.jwt.JwtUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthCpfHandlerTest {

    @Test
    void deveGerarToken() {

        JwtUtil jwtUtil = new JwtUtil("n7Q2zXE9+fR3GjG2Q1v9K2Wq4tYF8xX0Y0mK9P2qQ3w=");

        ClientesApiClient clientFake = mock(ClientesApiClient.class);
        when(clientFake.existsByCPF(any())).thenReturn(true);

        AuthCpfService service =
                new AuthCpfService(jwtUtil, clientFake);

        AuthCpfHandler handler =
                new AuthCpfHandler(service);


        AuthCpfResponse resp = handler.handleRequest(Map.of("body", "{\"cpf\":\"37401698854\"}"), null);

        assertNotNull(resp.token());
    }

}
