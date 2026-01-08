package com.fiap.pj.core.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;

public class ClientesApiClient {

    private final static String API_KEY_HEADER = "X-API-KEY";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;
    private final String apiKey;

    public ClientesApiClient(String baseUrl, String apiKey) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public boolean existsByCPF(String cpf) {
        return callGet("/interno/clientes/" + cpf);
    }

    private boolean callGet(String path) {
        try {

            var uri = URI.create(baseUrl + path);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header(API_KEY_HEADER, apiKey)
                    .timeout(Duration.ofSeconds(30))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return false;
            }

            ClienteResponse cliente =
                    objectMapper.readValue(response.body(), ClienteResponse.class);
            
            return Objects.nonNull(cliente);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar Core API", e);
        }
    }
}
