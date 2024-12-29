package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.AuthenticationRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;
import br.com.instacoffe.app.services.AuthenticateService;
import br.com.instacoffe.app.utils.http.HttpUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticationController(AuthenticateService authenticateService){
        this.authenticateService = authenticateService;
    }


    @PostMapping
    public ResponseEntity<UserResponseDto> handle(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto){
      UserResponseDto userResponseDto = authenticateService.handle(authenticationRequestDto);
      return HttpUtil.ok(userResponseDto);
    }
}
