package stelnet.filters.intel;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
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

    public IntelIsFaction(final FactionAPI faction) {
        this.factionId = faction.getId();
    }

    @Override
    public boolean accept(final IntelInfoPlugin object) {
        final FactionAPI faction = object.getFactionForUIColors();
        if (faction == null) {
            log.debug("Token faction is null");
            return false;
        }
        return factionId.equals(faction.getId());
    }
}
