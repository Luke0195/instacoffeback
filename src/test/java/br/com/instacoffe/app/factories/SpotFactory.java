package br.com.instacoffe.app.factories;

import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;

import java.util.Date;
import java.util.UUID;

public class SpotFactory {

    private SpotFactory(){}


    public static SpotRequestDto makeSpotRequestDto(){
        return new SpotRequestDto("valid_name", "valid_thumbnail", 30.40, new String[]{"Java", "Angular"});
    }

    public static Spot makeSpot(SpotRequestDto spotRequestDto){
        return new Spot(UUID.randomUUID().toString(), spotRequestDto.name(),spotRequestDto.thumbnail(), spotRequestDto.price(), spotRequestDto.techs(), new Date(), null);
    }

    public static SpotResponseDto makeSpotResponseDto(Spot entity){
        return new SpotResponseDto(entity.getId(), entity.getName(), entity.getThumbnail(), entity.getPrice(), entity.getTechs(), new Date(), null);
    }
}
