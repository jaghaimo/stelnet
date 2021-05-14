package stelnet.market;

import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;

import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.L10n;
import stelnet.market.view.LegacyIntel;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

public class MarketResultIntel extends BaseIntel {

    public final static String TAG = "stelnetMarket";

    private final IntelSubject intelSubject;

    public MarketResultIntel(FactionAPI f, SectorEntityToken s, IntelSubject i) {
        super(f, s);
        intelSubject = i;
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
        return new IntelInfo(
                intelSubject.getIntelTitle(),
                L10n.get("intelLocation"),
                getLocationNameWithSystem(),
                L10n.get("intelFaction"),
                getFactionWithRel()
        );
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return Collections.<Renderable>singletonList(new LegacyIntel(intelSubject, size));
    }

    @Override
    protected String getTag() {
        return TAG;
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
}
