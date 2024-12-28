package br.com.instacoffe.app.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public record AppointmentRequestDto(
        @NotEmpty(message = "The field user_id must be required")
        @JsonProperty("user_id")
        String userId,
        @NotEmpty(message = "The field spot_id must be required")
        @JsonProperty("spot_id")
        String spotId,
        @NotEmpty(message = "The field date must be required")
        String date) {

    @Override
    public String userId() {
        return userId;
    }

    @Override
    public String spotId() {
        return spotId;
    }

    @Override
    public String date() {
        return date;
    }
}
