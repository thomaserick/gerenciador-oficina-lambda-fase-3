package com.fiap.pj.core.client;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

record ClienteResponse(
        boolean isValid
) {
    @JsonCreator
    public ClienteResponse(Boolean value) {
        this(Objects.nonNull(value) && value);
    }
}