package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.dtos.request.AppointmentRequestDto;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;

import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.factories.SpotFactory;
import br.com.instacoffe.app.repositories.SpotRepository;
import br.com.instacoffe.app.repositories.UserRepository;
import br.com.instacoffe.app.services.exceptions.ResourceAlreadyExistsException;
import br.com.instacoffe.app.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.List;
import java.util.Optional;



@ExtendWith(SpringExtension.class)
class SpotServiceTest {

    @Mock
    private SpotRepository spotRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SpotService spotService;

    private SpotRequestDto spotRequestDto;
    private Spot spot;
    private SpotResponseDto spotResponseDto;
    private AppointmentRequestDto appointmentRequestDto;

    private void setupValues(){
        this.spotRequestDto = SpotFactory.makeSpotRequestDto();
        this.spot = SpotFactory.makeSpot(spotRequestDto);
        this.spotResponseDto = SpotFactory.makeSpotResponseDto(spot);
    }

    @BeforeEach
    void setup(){
        setupValues();
    }


    @DisplayName("add should throws ResourceAlreadyExists if the spot name is already taken")
    @Test
    void addShouldThrowsResourceAlreadyExistsIfSpotNameIsAlreadyTaken(){
        Mockito.when(spotRepository.findByName(Mockito.anyString()))
                .thenReturn(Optional.of(SpotFactory.makeSpot(SpotFactory.makeSpotRequestDto())));
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> {
            spotService.add(spotRequestDto);
        });
    }

    @DisplayName("add should returns a spot when valid data is provided")
    @Test
    void addShouldReturnsASpotWhenValidDataIsProvided(){;
     Mockito.when(spotRepository.findByName(spotRequestDto.name())).thenReturn(Optional.empty());
     Mockito.when(spotRepository.save(Mockito.any())).thenReturn(spot);
     SpotResponseDto spotResponseDto = spotService.add(spotRequestDto);
     Assertions.assertNotNull(spotResponseDto.id());
     Assertions.assertEquals("valid_name", spotResponseDto.name());
    }

    @DisplayName("loadSpots should returns a list of spots")
    @Test
    void loadSpotsReturnsAnSpot(){
        Mockito.when(spotRepository.findAll()).thenReturn(List.of(SpotFactory.makeSpot(spotRequestDto)));
        List<SpotResponseDto> responseDtoList = spotService.findAllUsers();
        Assertions.assertEquals(1, responseDtoList.size());
    }



    @DisplayName("addAppointment should throws if an invalid spotId is provided")
    @Test
    void addAppointmentShouldThrowsIfSpotIdNotExists(){
        String notExistingSpot = "invalid_spot_id";
        Mockito.when(spotRepository.findById(Mockito.any())).thenThrow(ResourceNotFoundException.class);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            spotService.addAppointment(notExistingSpot, appointmentRequestDto);
        });
    }

    @DisplayName("addAppointment should throws is an invalid userId is provided")
    @Test
    void addAppointmentShouldThrowsIfUserIdNotExists(){
        String validSpotId = "valid_id";
        AppointmentRequestDto invalidAppointRequestDto = new AppointmentRequestDto("invalid_id", "2024-12-28");
        Mockito.when(spotRepository.findById(validSpotId)).thenReturn(Optional.of(this.spot));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            spotService.addAppointment(validSpotId, invalidAppointRequestDto);
        });
    }






}