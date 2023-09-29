package roxor.games.roulette.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import roxor.games.roulette.game.RouletteWheel;
import roxor.games.roulette.game.WheelResult;
import roxor.games.roulette.model.request.PlayerBetDTO;
import roxor.games.roulette.model.request.SpinDTO;
import roxor.games.roulette.model.response.BetResultDTO;
import roxor.games.roulette.model.response.SpinResultDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RouletteServiceTest {

    @InjectMocks
    private RouletteService rouletteService;

    @Mock
    private RouletteWheel rouletteWheel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidSpinWithNoForcedPocket() {
        // Create a SpinDTO with no forced pocket and player bets
        SpinDTO spinDTO = SpinDTO.builder()
                .forcedPocket(null)
                .playerBets(Collections.singletonList(
                        PlayerBetDTO.builder()
                                .playerName("Sam")
                                .betType("number")
                                .pocket("4")
                                .betAmount("1.00")
                                .build()
                ))
                .build();

        when(rouletteWheel.spin(any())).thenReturn(WheelResult.create(10));
        SpinResultDTO result = rouletteService.spin(spinDTO);

        assertEquals(10, result.getPocket());
        List<BetResultDTO> betResults = result.getBetResults();
        assertEquals(1, betResults.size());
        BetResultDTO betResult = betResults.get(0);
        assertEquals("Sam", betResult.getPlayerBet().getPlayerName());
        assertEquals("number", betResult.getPlayerBet().getBetType());
        assertEquals("4", betResult.getPlayerBet().getPocket());
        assertEquals("1.00", betResult.getPlayerBet().getBetAmount());
        assertEquals("lose", betResult.getOutcome());
        assertEquals("0.00", betResult.getWinAmount());
    }

    @Test
    public void testValidSpinWithForcedPocket() {
        // Create a SpinDTO with a forced pocket and player bets
        SpinDTO spinDTO = SpinDTO.builder()
                .forcedPocket("36")
                .playerBets(Arrays.asList(
                        PlayerBetDTO.builder()
                                .playerName("Nick")
                                .betType("number")
                                .pocket("7")
                                .betAmount("1.00")
                                .build(),
                        PlayerBetDTO.builder()
                                .playerName("Jules")
                                .betType("number")
                                .pocket("36")
                                .betAmount("2.00")
                                .build()
                ))
                .build();

        when(rouletteWheel.spin(any())).thenReturn(WheelResult.create(36));
        SpinResultDTO result = rouletteService.spin(spinDTO);
        assertEquals(36, result.getPocket());
        List<BetResultDTO> betResults = result.getBetResults();
        assertEquals(2, betResults.size());
        BetResultDTO betResultNick = betResults.get(0);
        assertEquals("Nick", betResultNick.getPlayerBet().getPlayerName());
        assertEquals("number", betResultNick.getPlayerBet().getBetType());
        assertEquals("7", betResultNick.getPlayerBet().getPocket());
        assertEquals("1.00", betResultNick.getPlayerBet().getBetAmount());
        assertEquals("win", betResultNick.getOutcome());
        assertEquals("36.00", betResultNick.getWinAmount());

        BetResultDTO betResultJules = betResults.get(1);
        assertEquals("Jules", betResultJules.getPlayerBet().getPlayerName());
        assertEquals("number", betResultJules.getPlayerBet().getBetType());
        assertEquals("36", betResultJules.getPlayerBet().getPocket());
        assertEquals("2.00", betResultJules.getPlayerBet().getBetAmount());
        assertEquals("win", betResultJules.getOutcome());
        assertEquals("72.00", betResultJules.getWinAmount());
    }

    @Test
    public void testInvalidForcedPocket() {
        // Create a SpinDTO with an invalid forced pocket and player bets
        SpinDTO spinDTO = SpinDTO.builder()
                .forcedPocket("invalid")
                .playerBets(Collections.singletonList(
                        PlayerBetDTO.builder()
                                .playerName("Bob")
                                .betType("number")
                                .pocket("10")
                                .betAmount("2.50")
                                .build()
                ))
                .build();
        assertThrows(IllegalArgumentException.class, () -> rouletteService.spin(spinDTO));
    }

    @Test
    public void testNoBetsPlaced() {
        // Create a SpinDTO with no player bets and no forced pocket
        SpinDTO spinDTO = SpinDTO.builder()
                .forcedPocket(null)
                .playerBets(Collections.emptyList())
                .build();

        when(rouletteWheel.spin(any())).thenReturn(WheelResult.create(15));

        SpinResultDTO result = rouletteService.spin(spinDTO);

        assertEquals(15, result.getPocket());
        List<BetResultDTO> betResults = result.getBetResults();
        assertTrue(betResults.isEmpty());
    }

}
