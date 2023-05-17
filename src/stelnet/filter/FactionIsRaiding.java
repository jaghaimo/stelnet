package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class FactionIsRaiding extends FactionFilter {

    @Override
    protected boolean acceptFaction(FactionAPI faction) {
        if (faction.getId() == Factions.LUDDIC_PATH) {
            return true;
        }
        return faction.getCustomBoolean(Factions.CUSTOM_MAKES_PIRATE_BASES);
    }
}
