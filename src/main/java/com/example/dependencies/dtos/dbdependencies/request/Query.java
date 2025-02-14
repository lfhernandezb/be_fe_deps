package com.example.dependencies.dtos.dbdependencies.request;

import com.example.dependencies.dtos.Term;

@lombok.Data
@lombok.Builder
public class Query {
    private Term term;
}
