package br.com.instacoffe.app.factories;

import br.com.instacoffe.app.dtos.request.AuthenticationRequestDto;

public class AuthenticationFactory {

    private AuthenticationFactory(){};


    public static AuthenticationRequestDto makeAuthenticationRequestDto(){
        return new AuthenticationRequestDto("valid_mail@mail.com");
    }
}
