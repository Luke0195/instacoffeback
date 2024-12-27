package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.services.SpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/spots")
public class SpotController {

    @Autowired
    private SpotService service;

    @PostMapping
    public ResponseEntity<SpotResponseDto> addSpot(@Valid @RequestBody SpotRequestDto spotRequestDto){
      SpotResponseDto spotResponseDto = service.add(spotRequestDto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(spotResponseDto.id()).toUri();
      return ResponseEntity.created(uri).body(spotResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<SpotResponseDto>> loadSpots(){
        return ResponseEntity.status(HttpStatus.OK).body(List.of(new SpotResponseDto("id", "any_name", "any_thumb", 30.0, new String[]{"Java"}, new Date(), null )));
    }
}
