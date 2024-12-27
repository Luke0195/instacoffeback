package br.com.instacoffe.app.domain.usecases.spots;

import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.dtos.response.UserResponseDto;

import java.util.List;

public interface LoadSpotsUseCase {

    List<SpotResponseDto> findAllUsers();
}
