package br.com.instacoffe.app.factories;

import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;

import java.util.Date;

public class UserFactory {

    private UserFactory(){}

    public static UserRequestDto makeUserRequestDto(){
        return new UserRequestDto("any_name", "any_mail@mail.com");
    }

    public static User makeUser(UserRequestDto requestDto){
        return new User("any_id", requestDto.name(), requestDto.name(), new Date(), new Date());
    }

    public static UserResponseDto makeUserResponseDto(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
