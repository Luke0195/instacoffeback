package br.com.instacoffe.app.controllers;

import br.com.instacoffe.app.dtos.request.AppointmentRequestDto;
import br.com.instacoffe.app.services.SpotService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AppointmentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SpotService spotService;

    @Autowired
    private MockMvc mockMvc;


    @DisplayName("POST - Should returns 400 if no  user_id is provided")
    @Test
    void addAppointmentUseCaseShouldReturnsBadRequestIfNoUserIdIsProvided() throws Exception{
        AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto(null, "2024-12-28");
        String jsonBody = objectMapper.writeValueAsString(appointmentRequestDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/spots/1/appointment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        );
       resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
