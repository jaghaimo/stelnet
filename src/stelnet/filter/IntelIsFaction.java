package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Based on `getFactionForUIColors` faction.
 */
@EqualsAndHashCode(callSuper = false)
@Log4j
@RequiredArgsConstructor
public class IntelIsFaction extends IntelFilter {

    private final String factionId;

    public IntelIsFaction(FactionAPI faction) {
        this.factionId = faction.getId();
    }

    protected boolean acceptIntel(BaseIntelPlugin intel) {
        FactionAPI faction = intel.getFactionForUIColors();
        if (faction == null) {
            log.debug("Token faction is null");
            return false;
        }
        return factionId.equals(faction.getId());
    }
}
