package br.com.instacoffe.app.services;

import br.com.instacoffe.app.dtos.request.AuthenticationRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;
import br.com.instacoffe.app.factories.AuthenticationFactory;
import br.com.instacoffe.app.factories.UserFactory;
import br.com.instacoffe.app.repositories.UserRepository;
import br.com.instacoffe.app.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
class AuthenticateServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticateService authenticateService;

    private AuthenticationRequestDto authenticationRequestDto;


    void setup(){
        this.authenticationRequestDto = AuthenticationFactory.makeAuthenticationRequestDto();
    }

    void setupValues(){
        setup();
    }

    @DisplayName("handle should throws if an invalid email is provided")
    @Test
    void handleShouldThrowsIfAnInvalidEmailIsProvided(){
        AuthenticationRequestDto authenticationRequestDto= new AuthenticationRequestDto("invalid_email@mail.com");
        Mockito.when(userRepository.findByEmail(authenticationRequestDto.email())).thenThrow(ResourceNotFoundException.class);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            authenticateService.handle(authenticationRequestDto);
        });
    }

    @DisplayName("handle should returns UserResponseDto when valid valid data is provided")
    @Test
    void handleShouldReturnsWhenValidDataIsProvided(){
        AuthenticationRequestDto authenticationRequestDto = AuthenticationFactory.makeAuthenticationRequestDto();
        Mockito.when(userRepository.findByEmail(authenticationRequestDto.email())).thenReturn(Optional.of(UserFactory.makeUser(UserFactory.makeUserRequestDto())));
        UserResponseDto userResponseDto = authenticateService.handle(authenticationRequestDto);
        Assertions.assertNotNull(userResponseDto.id());
    }
}