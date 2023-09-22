package roxor.games.roulette.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
@EqualsAndHashCode
public class PlayerBetDTO {

    private final String playerName;
    private final String betType;
    private final String pocket;
    private final String betAmount;

    @JsonCreator
    public PlayerBetDTO(
            @JsonProperty("playerName") String playerName,
            @JsonProperty("betType") String betType,
            @JsonProperty("pocket") String pocket,
            @JsonProperty("betAmount") String betAmount
    ) {

        this.playerName = playerName;
        this.betType = betType;
        this.pocket = pocket;
        this.betAmount = betAmount;
    }
}
