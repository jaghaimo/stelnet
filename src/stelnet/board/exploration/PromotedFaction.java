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

    @Override
    public String getFamily() {
        return "FACTIONS";
    }

    @Override
    public int getShortcut() {
        return 0;
    }

    @Override
    public String getTitle() {
        return faction.getDisplayName();
    }
}
