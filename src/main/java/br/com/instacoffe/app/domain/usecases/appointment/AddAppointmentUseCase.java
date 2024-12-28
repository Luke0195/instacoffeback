package br.com.instacoffe.app.domain.usecases.appointment;

import br.com.instacoffe.app.dtos.request.AppointmentRequestDto;
import br.com.instacoffe.app.dtos.response.AppointmentResponseDto;

public interface AddAppointmentUseCase {
    AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto);
}
