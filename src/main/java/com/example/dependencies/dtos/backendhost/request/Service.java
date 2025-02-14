package com.example.dependencies.dtos.backendhost.request;

import com.example.dependencies.dtos.Terms;

@lombok.Data
@lombok.Builder
public class Service {
    private Terms terms;
    private ServiceAggs aggs;
}
