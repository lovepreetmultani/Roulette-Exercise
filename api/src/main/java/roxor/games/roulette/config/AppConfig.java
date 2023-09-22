package roxor.games.roulette.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roxor.games.roulette.service.RouletteService;

@Configuration
public class AppConfig {

    @Bean
    public RouletteService rouletteService() {
        return new RouletteService();
    }

}
