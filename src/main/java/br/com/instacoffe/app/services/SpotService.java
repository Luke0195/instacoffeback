package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.domain.usecases.spots.AddSpotUseCase;
import br.com.instacoffe.app.domain.usecases.spots.LoadSpotsUseCase;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;

import br.com.instacoffe.app.repositories.SpotRepository;
import br.com.instacoffe.app.services.exceptions.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpotService implements AddSpotUseCase, LoadSpotsUseCase {

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

    @Override
    @Cacheable(value = "users")
    public List<SpotResponseDto> findAllUsers() {
        List<Spot> spots = repository.findAll();
        return spots.stream().map(SpotService::makeSpotResponseDto).toList();
    }


    private static Spot makeSpot(SpotRequestDto spotRequestDto){
     Spot spot = new Spot();
     spot.setName(spotRequestDto.name());
     spot.setThumbnail(spotRequestDto.thumbnail());
     spot.setTechs(spotRequestDto.techs());
     spot.setPrice(spotRequestDto.price());
     return spot;
    }

    private static SpotResponseDto makeSpotResponseDto(Spot spot){
        return new SpotResponseDto(spot.getId(), spot.getName(), spot.getThumbnail(), spot.getPrice(), spot.getTechs(),spot.getCreatedAt(), spot.getUpdatedAt());
    }


}
