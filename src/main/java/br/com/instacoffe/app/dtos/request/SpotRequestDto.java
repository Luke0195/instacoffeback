package br.com.instacoffe.app.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SpotRequestDto(
        @NotEmpty(message = "The field name must be required")
        String name,
        String thumbnail,
        BigDecimal price,
        @NotNull(message = "The field techs must be required")
        String[] techs) {

    @Override
    public String name() {
        return name;
    }

    @Override
    public String thumbnail() {
        return thumbnail;
    }

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String[] techs() {
        return techs;
    }
}
