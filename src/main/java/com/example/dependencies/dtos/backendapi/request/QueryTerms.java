package com.example.dependencies.dtos.backendapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.Builder
public class QueryTerms {
    @JsonProperty("http.request.method")
    private String[] httpRequestMethod;
}