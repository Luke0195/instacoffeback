package br.com.instacoffe.app.services;

import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.domain.usecases.AddUser;
import br.com.instacoffe.app.dtos.request.UserRequestDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;
import br.com.instacoffe.app.repositories.UserRepository;
import br.com.instacoffe.app.services.exceptions.ResourceAlreadyExistsException;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements AddUser {


    private UserRepository repository;

    @Override
    public UserResponseDto add(UserRequestDto userRequestDto) {
      Optional<User> userAlreadyExists = repository.findByEmail(userRequestDto.email());
      if(userAlreadyExists.isPresent()) throw new ResourceAlreadyExistsException("This e-mail is already taken");
      return null;

    }
}
