package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.domain.usecases.authentication.AuthenticateUseCase;
import br.com.instacoffe.app.dtos.request.AuthenticationRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;
import br.com.instacoffe.app.repositories.UserRepository;
import br.com.instacoffe.app.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateService implements AuthenticateUseCase {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticateService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto handle(AuthenticationRequestDto authenticationRequestDto) {
        User userAlreadyExists = userRepository.findByEmail(authenticationRequestDto.email())
                .orElseThrow(() -> new ResourceNotFoundException("This e-mail is not found!"));
        return new UserResponseDto(userAlreadyExists.getId(), userAlreadyExists.getName(),userAlreadyExists.getEmail(),
                userAlreadyExists.getCreatedAt(), userAlreadyExists.getUpdatedAt());
    }
}
