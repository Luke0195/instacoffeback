package br.com.instacoffe.app.factories;

import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;

import java.util.Date;

public class UserFactory {

    private UserFactory(){}


    public static UserRequestDto makeUserRequestDto(){
        return new UserRequestDto("valid_name", "valid_mail@mail.com");
    }

    public static User makeUser(UserRequestDto userRequestDto){
        return new User("any_id", "valid_name", "valid_mail@mail.com", new Date(), null );
    }

    public static UserResponseDto makeUserResponseDto(User entity){
        return new UserResponseDto(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
