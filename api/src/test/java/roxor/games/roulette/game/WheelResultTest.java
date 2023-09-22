package roxor.games.roulette.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WheelResultTest {


    @Test
    void getPocketResultReturnsCorrectPocketNumber() {
        assertEquals(1, WheelResult.create(1).getPocketResult());
    }
}