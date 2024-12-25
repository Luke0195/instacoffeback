package br.com.instacoffe.app.dtos.response;

import br.com.instacoffe.app.domain.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record UserResponseDto(
        String id,
        String name,
        String email,
        @JsonProperty("created_at")
        Date createdAt,
        @JsonProperty("updated_at")
        Date updatedAt) {
    @Override
    public String id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public Date createdAt() {
        return createdAt;
    }

    @Override
    public Date updatedAt() {
        return updatedAt;
    }

   
}
