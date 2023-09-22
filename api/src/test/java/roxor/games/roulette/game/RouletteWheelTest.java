package roxor.games.roulette.game;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RouletteWheelTest {

    private RouletteWheel rouletteWheel = new RouletteWheel();

    @Test
    void cantSpinWithANullForcedPocket() {
        try {
            rouletteWheel.spin(null);
        } catch (NullPointerException e){
            assertEquals("forcedPocketNumber is marked non-null but is null", e.getMessage());
        }
    }

    @Test
    void canForcePocketNumber() {
        WheelResult result = rouletteWheel.spin(Optional.of(0));
        assertEquals(0, result.getPocketResult());
    }

    @Test
    void pocketResultFallsWithinExpectedBounds() {
        int spins = 1000;
        for (int i = 0; i < spins; i++) {
            int actualPocketNumber = rouletteWheel.spin(Optional.empty()).getPocketResult();
            assertTrue(0 <= actualPocketNumber && actualPocketNumber <= 36,
                    "Expected actualPocketNumber to be within the range [0,36] but was: " + actualPocketNumber);
        }
    }



}