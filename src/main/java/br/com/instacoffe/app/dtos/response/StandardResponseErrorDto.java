package br.com.instacoffe.app.dtos.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Set;

public record StandardResponseErrorDto(
        Instant timestamp,
        @JsonProperty("status_code")
        Integer status,
        String error,
        String message,
        String path,
        Set<FieldResponseDto> errors) {

    @Override
    public Instant timestamp() {
        return timestamp;
    }

    @Override
    public Integer status() {
        return status;
    }

    @Override
    public String error() {
        return error;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public Set<FieldResponseDto> errors() {
        return errors;
    }
}
