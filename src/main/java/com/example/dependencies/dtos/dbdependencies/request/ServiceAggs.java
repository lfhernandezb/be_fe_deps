package com.example.dependencies.dtos.dbdependencies.request;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
public class ServiceAggs {
    @JsonProperty("engine_type")
    private EngineType engineType;
}
