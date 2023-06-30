package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Factions implements ButtonAware {

    private final FactionAPI faction;

    @Override
    public String getId() {
        return faction.getId();
    }

    @Override
    public String getTitle() {
        return faction.getDisplayName();
    }
}
