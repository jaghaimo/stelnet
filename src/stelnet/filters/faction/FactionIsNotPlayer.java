package stelnet.filters.faction;

import com.fs.starfarer.api.campaign.FactionAPI;
import stelnet.filters.Filter;

public class FactionIsNotPlayer implements Filter<FactionAPI> {

    @Override
    public boolean accept(final FactionAPI object) {
        return !object.isPlayerFaction();
    }
}
