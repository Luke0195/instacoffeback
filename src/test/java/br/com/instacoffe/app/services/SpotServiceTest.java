package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.repositories.SpotRepository;
import br.com.instacoffe.app.services.exceptions.ResourceAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SpotServiceTest {

    @Mock
    private SpotRepository spotRepository;

    @InjectMocks
    private SpotService spotService;

    private SpotRequestDto spotRequestDto;


    @DisplayName("add should throws ResourceAlreadyExists if the spot name is already taken")
    @Test
    void addShouldThrowsResourceAlreadyExistsIfSpotNameIsAlreadyTaken(){
        spotRequestDto = new SpotRequestDto("valid_name", "any_thumbnail", BigDecimal.valueOf(0), new String[]{"React"});
        Mockito.when(spotRepository.findByName(Mockito.any())).thenReturn(Optional.of(new Spot()));
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> {
            spotService.add(spotRequestDto);
        });
    }

    @DisplayName("add should returns a spot when valid data is provided")
    @Test
    void addShouldReturnsASpotWhenValidDataIsProvided(){
     spotRequestDto = new SpotRequestDto("valid_name", "any_thumbnail", BigDecimal.valueOf(0), new String[]{"React"});
     Mockito.when(spotRepository.findByName(spotRequestDto.name())).thenReturn(Optional.empty());
     Mockito.when(spotRepository.save(Mockito.any())).thenReturn(new Spot("any_id", spotRequestDto.name(), spotRequestDto.thumbnail(), spotRequestDto.price(), spotRequestDto.techs(), new Date(), null));
     SpotResponseDto spotResponseDto = spotService.add(spotRequestDto);
     Assertions.assertNotNull(spotResponseDto.id());
     Assertions.assertEquals("valid_name", spotResponseDto.name());
    }
}