package com.fiap.pj.core.app;

import com.fiap.pj.core.client.ClientesApiClient;
import com.fiap.pj.core.jwt.JwtUtil;
import com.fiap.pj.core.usecase.AuthCpfUseCase;
import com.fiap.pj.core.usecase.command.AutenticarCommand;
import com.fiap.pj.core.util.validator.CPFValidator;

public class AuthCpfService implements AuthCpfUseCase {

    private final JwtUtil jwtUtil;
    private final ClientesApiClient client;

    public AuthCpfService(JwtUtil jwtUtil, ClientesApiClient client) {
        this.jwtUtil = jwtUtil;
        this.client = client;
    }

    @Override
    public String handle(AutenticarCommand cmd) {

        if (!CPFValidator.isValid(cmd.cpf())) {
            throw new RuntimeException("CPF inválido " + cmd.cpf());
        }

        if (!client.existsByCPF(cmd.cpf())) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return jwtUtil.generateClienteToken(cmd.cpf());
    }

}
