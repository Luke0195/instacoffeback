package br.com.instacoffe.app.domain.usecases.users;

import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;

public interface AddUserUseCase {

    UserResponseDto add(UserRequestDto userRequestDto);

}
