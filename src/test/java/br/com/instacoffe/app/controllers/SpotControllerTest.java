package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.SpotRequestDto;
import br.com.instacoffe.app.dtos.response.SpotResponseDto;
import br.com.instacoffe.app.repositories.SpotRepository;
import br.com.instacoffe.app.services.SpotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArraysOfBoolean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpotControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private SpotRequestDto spotRequestDto;
    @Autowired
    private SpotService spotService;
    @Autowired
    private SpotRepository spotRepository;


    void setupValues(){
        this.spotRequestDto = new SpotRequestDto("any_name", "any_string", BigDecimal.valueOf(30.30), new String[]{"Java"});
    }

    @DisplayName("POST - Should returns 400 if no name is provided")
    @Test
    void addSpotShouldReturnsBadRequestWhenNoNameIsProvided() throws  Exception{
        spotRequestDto = new SpotRequestDto(null, "any_string", BigDecimal.valueOf(30.0), new String[]{"Java"});
        String jsonBody = objectMapper.writeValueAsString(spotRequestDto);
        ResultActions resultActions = mockMvc.perform(post("/spots")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("POST- Should returns 201 when valid data is provided")
    @Test
    void addSpotShouldReturnsAnSpotWhenValidDataIsProvided() throws  Exception{

        spotRequestDto = new SpotRequestDto("any_name", "any_thumbnail", BigDecimal.valueOf(30.0), new String[]{"React"});
        String jsonBody = objectMapper.writeValueAsString(spotRequestDto);
        ResultActions resultActions = mockMvc.perform(post("/spots")
                .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody));
        resultActions.andExpect(status().isCreated());
    }
}