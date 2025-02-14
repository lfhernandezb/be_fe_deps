package com.example.dependencies.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
public class Term {
    @JsonProperty("span.type")
    private String spanType;
    @JsonProperty("span.subtype")
    private String spanSubtype;
    @JsonProperty("metric.set")
    private String metricSet;
}
