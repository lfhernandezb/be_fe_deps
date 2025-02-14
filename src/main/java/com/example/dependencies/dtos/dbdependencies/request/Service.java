package com.example.dependencies.dtos.dbdependencies.request;

import com.example.dependencies.dtos.Terms;

@lombok.Data
@lombok.Builder
public class Service {
    private Terms terms;
    private ServiceAggs aggs;
}
