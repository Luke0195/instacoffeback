package br.com.instacoffe.app.dtos.response;

public record FieldResponseDto(String name, String description) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }
}
