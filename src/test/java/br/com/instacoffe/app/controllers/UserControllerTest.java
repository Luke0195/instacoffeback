package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.domain.models.User;
import br.com.instacoffe.app.dtos.request.UserRequestDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private UserRequestDto userRequestDto;

    @DisplayName("POST- should returns 400 if no name is provided")
    @Test
    void addUserShouldReturnsBadRequestWhenNoNameIsProvided()throws Exception{
        UserRequestDto requestDto = new UserRequestDto(null, "any_mail@mail.com");
        String jsonBody = objectMapper.writeValueAsString(requestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody));
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("POST - should returns 400 if no email is provided")
    @Test
    void addUserShouldReturnsBadRequestWhenNoEmailIsProvided() throws Exception{
        UserRequestDto requestDto = new UserRequestDto("any_name", null);
        String jsonBody = objectMapper.writeValueAsString(requestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody));
        resultActions.andExpect(status().isBadRequest());
    }
/*
    @DisplayName("POST- Should returns 400 if invalid e-amail is provided")
    @Test
    void addUserShouldReturnsBadRequestWhenInvalidEmailIsProvided() throws Exception{
        UserRequestDto requestDto = new UserRequestDto("any_name", "any_mail");
        String jsonBody = objectMapper.writeValueAsString(requestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
        resultActions.andExpect(status().isBadRequest());
    }
    */
 

}