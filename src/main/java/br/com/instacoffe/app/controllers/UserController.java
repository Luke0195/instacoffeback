package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;
import br.com.instacoffe.app.services.UserService;
import br.com.instacoffe.app.utils.http.HttpUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = service.add(userRequestDto);
        return HttpUtil.created(userResponseDto, userResponseDto.id());
    }


}
