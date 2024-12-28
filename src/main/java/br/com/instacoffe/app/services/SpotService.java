package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.Appointment;
import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.domain.usecases.appointment.AddAppointmentUseCase;
import br.com.instacoffe.app.domain.usecases.spots.AddSpotUseCase;
import br.com.instacoffe.app.domain.usecases.spots.LoadSpotsUseCase;
import br.com.instacoffe.app.dtos.request.AppointmentRequestDto;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.AppointmentResponseDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;

import br.com.instacoffe.app.repositories.AppointmentRepository;
import br.com.instacoffe.app.repositories.SpotRepository;
import br.com.instacoffe.app.repositories.UserRepository;
import br.com.instacoffe.app.services.exceptions.ResourceAlreadyExistsException;
import br.com.instacoffe.app.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SpotService implements AddSpotUseCase, LoadSpotsUseCase, AddAppointmentUseCase {

    private final SpotRepository spotRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository, UserRepository userRepository, AppointmentRepository appointmentRepository){
        this.spotRepository = spotRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public SpotResponseDto add(SpotRequestDto spotRequestDto) {
       Optional<Spot> findSpotByName = spotRepository.findByName(spotRequestDto.name());
       if(findSpotByName.isPresent()) throw new ResourceAlreadyExistsException("This name is already taken!");
       Spot spot = makeSpot(spotRequestDto);
       spot = spotRepository.save(spot);
       return new SpotResponseDto(spot.getId(), spot.getName(), spot.getThumbnail(), spot.getPrice(),
               spot.getTechs(), spot.getCreatedAt(), spot.getUpdatedAt());
    }

    @Override
    @Cacheable(value = "users")
    public List<SpotResponseDto> findAllUsers() {
        List<Spot> spots = spotRepository.findAll();
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


    @Override
    public AppointmentResponseDto addAppointment(String id,AppointmentRequestDto appointmentRequestDto) {
      Spot spotAlreadyExists = spotRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("This spot id is not found!"));
      User user = userRepository.findById(appointmentRequestDto.userId()).orElseThrow(() -> new ResourceNotFoundException("This user id is not found"));
      Appointment appointment = new Appointment();
      appointment.setSpot(spotAlreadyExists);
      appointment.setUser(user);
      appointment.setApproved(Boolean.FALSE);
      appointment.setDate(LocalDate.now().toString());
      appointment = appointmentRepository.save(appointment);
      return new AppointmentResponseDto(appointment.getId(), appointment.getUser(), appointment.getSpot(), appointment.getApproved(), appointment.getDate(), appointment.getCreatedAt(), appointment.getUpdatedAt());
    }
}
