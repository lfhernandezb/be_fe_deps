package com.example.dependencies.dtos.backendapi.response;

import java.io.IOException;

public enum Key {
    GET, POST, PUT, DELETE;

    public String toValue() {
        switch (this) {
            case GET: return "GET";
            case POST: return "POST";
            case PUT: return "PUT";
            case DELETE: return "DELETE";
        }
        return null;
    }

    public static Key forValue(String value) throws IOException {
        if (value.equals("GET")) return GET;
        if (value.equals("POST")) return POST;
        if (value.equals("PUT")) return PUT;
        if (value.equals("DELETE")) return DELETE;
        throw new IOException("Cannot deserialize Key");
    }
}
