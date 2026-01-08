package com.fiap.pj.core.usecase;

import com.fiap.pj.core.usecase.command.AutenticarCommand;

public interface AuthCpfUseCase {

    String handle(AutenticarCommand cmd);
}
