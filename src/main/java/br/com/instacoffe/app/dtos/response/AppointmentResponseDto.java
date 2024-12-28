package br.com.instacoffe.app.dtos.response;

import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.domain.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record AppointmentResponseDto(
        String id,
        User user,
        Spot spot,
        Boolean approved,
        String date,
        @JsonProperty("created_at")
        Date createdAt,
        @JsonProperty("updated_at")
        Date updatedAt) {
}
