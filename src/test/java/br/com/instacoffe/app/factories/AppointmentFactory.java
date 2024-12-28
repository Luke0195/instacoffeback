package br.com.instacoffe.app.factories;

import br.com.instacoffe.app.domain.models.Appointment;
import br.com.instacoffe.app.domain.models.Spot;
import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.dtos.request.AppointmentRequestDto;
import br.com.instacoffe.app.dtos.response.AppointmentResponseDto;


import java.time.LocalDate;
import java.util.Date;

public class AppointmentFactory {

    private AppointmentFactory(){}

    public static AppointmentRequestDto makeAppointmentRequestDto(){
        return new AppointmentRequestDto("valid_id", LocalDate.now().toString());
    }

    public static Appointment makeAppointmentResponseDto(AppointmentRequestDto appointmentRequestDto){
        User user = UserFactory.makeUser(UserFactory.makeUserRequestDto());
        Spot spot = SpotFactory.makeSpot(SpotFactory.makeSpotRequestDto());
        return new Appointment("valid_id", user, spot, LocalDate.now().toString(), Boolean.FALSE, new Date(),
                null );
    }

    public static AppointmentResponseDto makeAppointmentResponseDto(Appointment entity){
        return new AppointmentResponseDto(entity.getId(), entity.getUser(), entity.getSpot(), entity.getApproved(),
                entity.getDate(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
