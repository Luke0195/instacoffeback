package br.com.instacoffe.app.domain.usecases;

import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;

public interface AddUser {

    UserResponseDto add(UserRequestDto userRequestDto);

}
