package roxor.games.roulette.game;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.security.SecureRandom;
import java.util.Optional;

public class RouletteWheel {

    private final SecureRandom secureRandom = new SecureRandom();

    public WheelResult spin(@NonNull Optional<Integer> forcedPocketNumber) {

        int pocketResult = secureRandom.nextInt(37);
        WheelResult result = WheelResult.create(pocketResult);

        if (forcedPocketNumber.isPresent()){
            result = WheelResult.create(forcedPocketNumber.get());
        }

        return result;
    }

}
