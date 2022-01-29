package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class FactionIsShown extends FactionFilter {

    @Override
    protected boolean acceptFaction(FactionAPI faction) {
        return faction.isShowInIntelTab();
    }
}
