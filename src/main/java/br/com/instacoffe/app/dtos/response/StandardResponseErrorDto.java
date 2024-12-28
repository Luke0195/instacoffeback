package br.com.instacoffe.app.dtos.response;


import java.time.Instant;
import java.util.Set;

public record StandardResponseErrorDto(
        Instant timestamp,
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
