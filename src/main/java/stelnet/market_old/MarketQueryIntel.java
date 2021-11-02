package stelnet.market_old;

import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;

import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.L10n;
import stelnet.market_old.intel.subject.IntelSubject;
import stelnet.market_old.view.LegacyIntel;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

public class MarketQueryIntel extends BaseIntel {

    public final static String TAG = "stelnetMarket";

    private final IntelSubject intelSubject;

    public MarketQueryIntel(FactionAPI f, SectorEntityToken s, IntelSubject i) {
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
        ending = !intelSubject.isAvailable() || intelSubject.isStale();
    }
}
