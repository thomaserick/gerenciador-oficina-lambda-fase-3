package com.fiap.pj.core.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtil {

    public static final String CLIENTE_TYPE = "CLIENTE";
    public static final int EXPIRATION_CLIENTE_HOURS = 1;

    private final Key key;

    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateClienteToken(String cpf) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tipo", CLIENTE_TYPE);
        claims.put("scope", "CLIENT_API");

        return createToken(claims, cpf, EXPIRATION_CLIENTE_HOURS);
    }

    private String createToken(
            Map<String, Object> claims,
            String subject,
            int expirationHours
    ) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(
                        Date.from(
                                Instant.now().plus(expirationHours, ChronoUnit.HOURS)
                        )
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
