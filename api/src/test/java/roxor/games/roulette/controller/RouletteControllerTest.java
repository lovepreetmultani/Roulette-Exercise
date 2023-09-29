package roxor.games.roulette.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import roxor.games.roulette.model.request.PlayerBetDTO;
import roxor.games.roulette.model.request.SpinDTO;
import roxor.games.roulette.model.response.SpinResultDTO;
import roxor.games.roulette.service.RouletteService;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RouletteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RouletteService rouletteService;

    @Test
    public void testSpinEndpoint() throws Exception {
        // Create a sample SpinDTO
        SpinDTO spinDTO = SpinDTO.builder()
                .forcedPocket(null) // No forced pocket
                .playerBets(Collections.singletonList(
                        PlayerBetDTO.builder()
                                .playerName("Sam")
                                .betType("number")
                                .pocket("4")
                                .betAmount("1.00")
                                .build()
                ))
                .build();

        SpinResultDTO spinResultDTO = SpinResultDTO.builder()
                .pocket(30)
                .betResults(Collections.emptyList())
                .build();

        when(rouletteService.spin(any(SpinDTO.class))).thenReturn(spinResultDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/roulette/spin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spinDTO)))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        SpinResultDTO responseDTO = objectMapper.readValue(content, SpinResultDTO.class);
        assertEquals(spinResultDTO, responseDTO);
    }
}
