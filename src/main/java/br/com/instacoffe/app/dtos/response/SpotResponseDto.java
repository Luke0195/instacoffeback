package br.com.instacoffe.app.dtos.response;

import java.math.BigDecimal;
import java.util.Date;

public record SpotResponseDto(
        String id,
        String name,
        String thumbnail,
        Double price,
        String[] techs,
        Date createdAt,
        Date updatedAt
        ) {
    @Override
    public String id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String thumbnail() {
        return thumbnail;
    }

    @Override
    public Double price() {
        return price;
    }

    @Override
    public String[] techs() {
        return techs;
    }
}
