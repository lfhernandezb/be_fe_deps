package com.example.dependencies.dtos.apidependencies.request;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
public class Bool {
    @JsonProperty("should")
    private Should[] shoulds;
}
