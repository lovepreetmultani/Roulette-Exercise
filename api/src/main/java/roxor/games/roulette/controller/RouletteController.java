package roxor.games.roulette.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import roxor.games.roulette.model.request.SpinDTO;
import roxor.games.roulette.model.response.SpinResultDTO;
import roxor.games.roulette.service.RouletteService;



@RestController
@RequestMapping("/v1/roulette")
@AllArgsConstructor
public class RouletteController {

    private final Logger logger = LoggerFactory.getLogger(RouletteController.class);

    private final RouletteService rouletteService;

    @PostMapping("/spin")
    @ResponseStatus(HttpStatus.OK)
    public SpinResultDTO spin(@RequestBody SpinDTO spinDTO) {
        logger.info("SpinDTO: " + spinDTO);

        SpinResultDTO spinResultDTO = rouletteService.spin(spinDTO);

        logger.info("SpinResultDTO: " + spinResultDTO);

        return spinResultDTO;
    }
}
