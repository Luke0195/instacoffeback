package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.AuthenticationRequestDto;
import br.com.instacoffe.app.factories.AuthenticationFactory;
import br.com.instacoffe.app.services.AuthenticateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private AuthenticationRequestDto authenticationRequestDto;
    @MockitoBean
    private AuthenticateService authenticateService;

    @Autowired
    private ObjectMapper objectMapper;

    void setupValues(){
        this.authenticationRequestDto = AuthenticationFactory.makeAuthenticationRequestDto();
    }

    void setup(){
        setupValues();
    }

    @DisplayName("POST - Should return 400 if no email is provided")
    @Test
    void handleShouldReturnsBadRequestIfEmailIsProvided() throws Exception{
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto(null);
        String jsonBody = objectMapper.writeValueAsString(requestDto);
        ResultActions resultActions = mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("POST - Should return 400 if an invalid e-mail is provided")
    @Test
    void handleShouldReturnsBadRequestIfInvalidEmailIsProvided() throws Exception{
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("invalid_mail");
        String jsonBody = objectMapper.writeValueAsString(requestDto);
        ResultActions resultActions = mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("POST - Should returns 201 if valid data is provided")
    @Test
    void handleShouldReturnsAnUserWhenValidDataIsProvided() throws Exception{
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("lucas@mail.com");
        String jsonBody = objectMapper.writeValueAsString(requestDto);
        ResultActions resultActions = mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
        resultActions.andExpect(status().isOk());
    }
}


