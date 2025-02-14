package com.example.dependencies.dtos.dbdependencies.request;

import com.example.dependencies.dtos.Terms;

@lombok.Data
@lombok.Builder
public class Instance {
    private Terms terms;
    private InstanceAggs aggs;
}
