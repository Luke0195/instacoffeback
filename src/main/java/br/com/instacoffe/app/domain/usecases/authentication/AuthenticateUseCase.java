package br.com.instacoffe.app.domain.usecases.authentication;

import br.com.instacoffe.app.dtos.request.AuthenticationRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;

public interface AuthenticateUseCase {
    UserResponseDto handle(AuthenticationRequestDto authenticationRequestDto);
}
