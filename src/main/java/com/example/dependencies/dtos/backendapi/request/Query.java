package com.example.dependencies.dtos.backendapi.request;

@lombok.Data
@lombok.Builder
public class Query {
    private QueryTerms terms;
}
