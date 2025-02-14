package com.example.dependencies.dtos.dbdependencies.response;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class ServiceBucket {
    private String key;
    private Long docCount;
    @JsonProperty("engine_type")
    private EngineType engineType;
}
