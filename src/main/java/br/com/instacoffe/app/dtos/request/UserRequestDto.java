package br.com.instacoffe.app.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(
        @NotEmpty(message = "The field name must be required")
        String name,
        @NotEmpty(message = "The field e-mail must be required")
        @Email(message = "Please provided a valid email")
        String email) {

    @Override
    public String email() {
        return email;
    }

    @Override
    public String name() {
        return name;
    }
}
