package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.services.SpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spots")
public class SpotController {

    @Autowired
    private SpotService service;

    @PostMapping
    public ResponseEntity<SpotResponseDto> addSpot(@Valid @RequestBody SpotRequestDto spotRequestDto){
        service.add(spotRequestDto);
        return null;
    }
}
