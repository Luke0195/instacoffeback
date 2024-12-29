package br.com.instacoffe.app.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequestDto(
        @NotEmpty(message = "The field e-mail is provided")
        @Email(message = "Please provided a valid e-mail is provided")
        String email) {

    @Override
    public String email() {
        return email;
    }
}
