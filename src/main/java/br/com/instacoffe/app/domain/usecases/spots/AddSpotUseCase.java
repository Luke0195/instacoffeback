package br.com.instacoffe.app.domain.usecases.spots;

import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;

public interface AddSpotUseCase {
    SpotResponseDto add(SpotRequestDto spotRequestDto);
}
