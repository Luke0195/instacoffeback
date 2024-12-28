package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.dtos.request.AppointmentRequestDto;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;

import br.com.instacoffe.app.dtos.response.SpotResponseDto;
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


import java.util.Date;
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

    private Spot existingSpot;

    private AppointmentRequestDto validAppointmentRequestDto;


    private void setUpValues(){
        this.existingSpot = new Spot("any_id", "any_name", "any_thumbnail", 30.0, new String[]{"java"}, new Date(), null);
        this.validAppointmentRequestDto = new AppointmentRequestDto("valid_id", "2024-12-28");
    }

    @BeforeEach
    void setup(){
        this.setUpValues();
    }


    @DisplayName("add should throws ResourceAlreadyExists if the spot name is already taken")
    @Test
    void addShouldThrowsResourceAlreadyExistsIfSpotNameIsAlreadyTaken(){
        Mockito.when(spotRepository.findByName(Mockito.any())).thenReturn(Optional.of(this.existingSpot));
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> {
            spotService.add(spotRequestDto);
        });
    }

    @DisplayName("add should returns a spot when valid data is provided")
    @Test
    void addShouldReturnsASpotWhenValidDataIsProvided(){;
     Mockito.when(spotRepository.findByName(spotRequestDto.name())).thenReturn(Optional.empty());
     Mockito.when(spotRepository.save(Mockito.any())).thenReturn(this.existingSpot);
     SpotResponseDto spotResponseDto = spotService.add(spotRequestDto);
     Assertions.assertNotNull(spotResponseDto.id());
     Assertions.assertEquals("valid_name", spotResponseDto.name());
    }

    @DisplayName("loadSpots should returns a list of spots")
    @Test
    void loadSpotsReturnsAnSpot(){
        Mockito.when(spotRepository.findAll()).thenReturn(List.of(this.existingSpot));
        List<SpotResponseDto> responseDtoList = spotService.findAllUsers();
        Assertions.assertEquals(1, responseDtoList.size());
    }



    @DisplayName("addAppointment should throws if an invalid spotId is provided")
    @Test
    void addAppointmentShouldThrowsIfSpotIdNotExists(){
        String notExistingSpot = "invalid_spot_id";
        Mockito.when(spotRepository.findById(Mockito.any())).thenThrow(ResourceNotFoundException.class);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            spotService.addAppointment(notExistingSpot, this.validAppointmentRequestDto);
        });
    }

    @DisplayName("addAppointment should throws is an invalid userId is provided")
    @Test
    void addAppointmentShouldThrowsIfUserIdNotExists(){
        String validSpotId = "valid_id";
        AppointmentRequestDto invalidAppointRequestDto = new AppointmentRequestDto("invalid_id", "2024-12-28");
        Mockito.when(spotRepository.findById(validSpotId)).thenReturn(Optional.of(this.existingSpot));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            spotService.addAppointment(validSpotId, invalidAppointRequestDto);
        });
    }




}