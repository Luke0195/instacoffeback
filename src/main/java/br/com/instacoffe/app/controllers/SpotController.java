package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.AppointmentRequestDto;
import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.AppointmentResponseDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.services.SpotService;
import br.com.instacoffe.app.utils.http.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/spots")
@AllArgsConstructor
public class SpotController {

    @Autowired
    private SpotService service;

    @PostMapping
    public ResponseEntity<SpotResponseDto> addSpot(@Valid @RequestBody SpotRequestDto spotRequestDto){
      SpotResponseDto spotResponseDto = service.add(spotRequestDto);
      return HttpUtil.created(spotResponseDto, spotResponseDto.id());
    }

    @GetMapping
    public ResponseEntity<List<SpotResponseDto>> loadSpots(){
        return HttpUtil.ok(service.findAllUsers());
    }

    @PostMapping(value = "/{spotId}/appointment")
    public ResponseEntity<AppointmentResponseDto> addAppointment(@PathVariable String spotId, @Valid @RequestBody AppointmentRequestDto appointmentRequestDto){
        service.addAppointment(spotId, appointmentRequestDto);
        return null;
    }

}
