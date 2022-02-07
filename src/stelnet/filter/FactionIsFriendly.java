package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class FactionIsFriendly extends FactionFilter {

    @Override
    protected boolean acceptFaction(FactionAPI faction) {
        return faction.getRelToPlayer().isAtWorst(RepLevel.SUSPICIOUS);
    }
}
