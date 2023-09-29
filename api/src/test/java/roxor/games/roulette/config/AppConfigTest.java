package roxor.games.roulette.config;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import roxor.games.roulette.config.AppConfig;
import roxor.games.roulette.service.RouletteService;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppConfigTest {

    @Test
    public void testRouletteServiceBeanCreation() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            RouletteService rouletteService = context.getBean(RouletteService.class);
            assertNotNull(rouletteService);
        }
    }
}
