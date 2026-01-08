package com.fiap.pj.core.config;

import com.fiap.pj.core.app.AuthCpfService;
import com.fiap.pj.core.client.ClientesApiClient;
import com.fiap.pj.core.jwt.JwtUtil;
import com.fiap.pj.core.usecase.AuthCpfUseCase;

public class LambdaConfig {

    public static AuthCpfUseCase authCpfUseCase() {

        String jwtSecret = System.getenv("JWT_SECRET");
        String coreUrl = System.getenv("CORE_API_URL");
        String apiKey = System.getenv("INTERNAL_API_KEY");

        JwtUtil jwtUtil =
                new JwtUtil(jwtSecret);

        ClientesApiClient apiClient =
                new ClientesApiClient(coreUrl, apiKey);

        return new AuthCpfService(jwtUtil, apiClient);
    }
}
