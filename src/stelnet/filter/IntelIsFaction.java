package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@EqualsAndHashCode(callSuper = false)
@Log4j
@RequiredArgsConstructor
public class IntelIsFaction extends Filter {

    private final String factionId;

    public IntelIsFaction(FactionAPI faction) {
        this.factionId = faction.getId();
    }

    protected boolean acceptIntel(BaseIntelPlugin intel) {
        SectorEntityToken token = intel.getPostingLocation();
        if (token == null) {
            log.debug("Posting location is null");
            return false;
        }
        FactionAPI faction = token.getFaction();
        if (faction == null) {
            log.debug("Token faction is null");
            return false;
        }
        return factionId.equals(faction.getId());
    }
}
