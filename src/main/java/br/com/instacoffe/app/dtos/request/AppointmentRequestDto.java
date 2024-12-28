package br.com.instacoffe.app.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


public record AppointmentRequestDto(
        @NotEmpty(message = "The field user_id must be required")
        @JsonProperty("user_id")
        String userId,
        @NotEmpty(message = "The field date must be required")
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Date must be in the format yyyy-MM-dd")
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
