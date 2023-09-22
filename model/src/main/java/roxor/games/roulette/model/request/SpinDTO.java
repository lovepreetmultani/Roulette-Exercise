package roxor.games.roulette.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class SpinDTO {

    private final String forcedPocket;

    private final List<PlayerBetDTO> playerBets;

    @JsonCreator
    public SpinDTO(
            @JsonProperty("forcedPocket") String forcedPocket,
            @JsonProperty("playerBets") List<PlayerBetDTO> playerBets
    ) {
        this.forcedPocket = forcedPocket;
        this.playerBets = playerBets;
    }
}
