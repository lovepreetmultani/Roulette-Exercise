package roxor.games.roulette.service;

import org.springframework.beans.factory.annotation.Autowired;
import roxor.games.roulette.game.RouletteWheel;
import roxor.games.roulette.game.WheelResult;
import roxor.games.roulette.model.request.PlayerBetDTO;
import roxor.games.roulette.model.request.SpinDTO;
import roxor.games.roulette.model.response.BetResultDTO;
import roxor.games.roulette.model.response.SpinResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouletteService {

    private static PlayerBetDTO playerBetDTO;

    @Autowired
    private static RouletteWheel rouletteWheel;

    @Autowired
    private static WheelResult wheelResult;


    public SpinResultDTO spin(SpinDTO spinDTO) {
        List<PlayerBetDTO> bets = spinDTO.getPlayerBets();
        String forcedPocket = spinDTO.getForcedPocket();

        int spinResult=0;
        if (forcedPocket != null && !forcedPocket.isEmpty()) {
            try {
                spinResult = Integer.parseInt(forcedPocket);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid forced pocket number");
            }
        } else{
            spinResult = rouletteWheel.spin(Optional.empty()).getPocketResult();
        }

        List<BetResultDTO> betResults = resolveBets(bets, spinResult);
        return SpinResultDTO.builder()
                .pocket(spinResult)
                .betResults(betResults)
                .build();
    }

    private List<BetResultDTO> resolveBets(List<PlayerBetDTO> bets, int spinResult) {
        List<BetResultDTO> betResults = new ArrayList<>();
        if(bets.size() > 0){
            for (PlayerBetDTO bet : bets) {
                if (Integer.parseInt(bet.getPocket()) == spinResult) {
                    double winAmount = Double.parseDouble(bet.getBetAmount()) * 36.0;
                    betResults.add(new BetResultDTO(bet, "win", String.valueOf(winAmount)));
                } else {
                    betResults.add(new BetResultDTO(bet, "lose", "0.0"));
                }
            }
        }
        return betResults;
    }
}

