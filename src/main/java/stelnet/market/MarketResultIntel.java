package stelnet.market;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.BaseIntel;
import stelnet.IntelInfo;

public class MarketResultIntel extends BaseIntel {

    public final static String TAG = "stelnetMarket";

    private final IntelSubject intelSubject;

    public MarketResultIntel(FactionAPI f, SectorEntityToken s, IntelSubject i) {
        super(f, s);
        intelSubject = i;
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        // TODO: redo using Renderable
        intelSubject.createSmallDescription(info, width, height);
    }

    @Override
    public String getIcon() {
        return intelSubject.getIcon();
    }

    @Override
    public IntelSortTier getSortTier() {
        endIfInvalid();
        if (isEnding() || isEnded()) {
            return IntelSortTier.TIER_2;
        }
        return IntelSortTier.TIER_1;
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(intelSubject.getIntelTitle(), "Location", getLocationNameWithSystem(), "Faction",
                getFactionWithRel());
    }

    private void endIfInvalid() {
        ending = false;
        if (!intelSubject.isAvailable()) {
            ending = true;
        }
        if (!intelSubject.canAcquire()) {
            ending = true;
        }
    }

    @Override
    protected String getTag() {
        return TAG;
    }
}
