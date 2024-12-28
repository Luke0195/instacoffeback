package br.com.instacoffe.app.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;


public record AppointmentRequestDto(
        @NotEmpty(message = "The field user_id must be required")
        @JsonProperty("user_id")
        String userId,
        @NotEmpty(message = "The field date must be required")
        @JsonFormat(pattern = "yyyy-mm-dd")
        String date) {

    @Override
    public String userId() {
        return userId;
    }

    @Override
    public String date() {
        return date;
    }
}
