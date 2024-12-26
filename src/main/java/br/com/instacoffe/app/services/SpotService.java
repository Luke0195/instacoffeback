package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.domain.usecases.spots.AddSpotUseCase;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.repositories.SpotRepository;
import br.com.instacoffe.app.services.exceptions.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpotService implements AddSpotUseCase {

    @Autowired
    private SpotRepository repository;


    @Override
    public SpotResponseDto add(SpotRequestDto spotRequestDto) {
       Optional<Spot> findSpotByName = repository.findByName(spotRequestDto.name());
       if(findSpotByName.isPresent()) throw new ResourceAlreadyExistsException("This name is already taken!");
       Spot spot = makeSpot(spotRequestDto);
       spot = repository.save(spot);
       return new SpotResponseDto(spot.getId(), spot.getName(), spot.getThumbnail(), spot.getPrice(), spot.getTechs(), spot.getCreatedAt(), spot.getUpdatedAt());
    }


    private static Spot makeSpot(SpotRequestDto spotRequestDto){
        Spot spot = new Spot();
        spot.setName(spotRequestDto.name());
        spot.setThumbnail(spotRequestDto.thumbnail());
        spot.setTechs(spot.getTechs());
        spot.setPrice(spot.getPrice());
        return spot;
    }
}
