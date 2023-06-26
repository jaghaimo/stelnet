package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PromotedFaction implements IdAware {

    private final FactionAPI faction;

    @Override
    public String getId() {
        return faction.getId();
    }
}
