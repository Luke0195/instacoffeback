package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;
import br.com.instacoffe.app.factories.UserFactory;
import br.com.instacoffe.app.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;


@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserRequestDto userRequestDto;

    @BeforeEach
    void setup(){
        this.userRequestDto = UserFactory.makeUserRequestDto();
    }

    @DisplayName("AddUser should returns ResourceAlreadyExistsException when e-mail already exists")
    @Test
    void addUserShouldReturnsResourceAlreadyExistsWhenEmailAlreadyExists(){
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new User()));
        Assertions.assertThrows(RuntimeException.class, () -> {
           userService.add(userRequestDto);
        });
    }

    @DisplayName("AddUser should returns User when valid data is provided")
    @Test
    void addUserShouldReturnsAnUserResponseDtoWhenValidDataIsProvided(){
        Mockito.when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(UserFactory.makeUser(userRequestDto));
        UserResponseDto userResponseDto = userService.add(userRequestDto);
        Assertions.assertNotNull(userResponseDto.id());
        Assertions.assertEquals("valid_name", userResponseDto.name());
        Assertions.assertEquals("valid_mail@mail.com", userResponseDto.email());
    }

}