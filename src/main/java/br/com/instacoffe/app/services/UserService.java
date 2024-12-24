package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.usecases.AddUser;
import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;
import br.com.instacoffe.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements AddUser {

    @Override
    public UserResponseDto add(UserRequestDto userRequestDto) {
        return null;
    }
}
