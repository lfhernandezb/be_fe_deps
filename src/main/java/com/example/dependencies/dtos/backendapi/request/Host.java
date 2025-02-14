package com.example.dependencies.dtos.backendapi.request;

import com.example.dependencies.dtos.Terms;

@lombok.Data
@lombok.Builder
public class Host {
    private Terms terms;
    private HostAggs aggs;
}
