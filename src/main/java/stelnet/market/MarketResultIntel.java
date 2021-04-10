package stelnet.market;

import java.awt.Color;
import java.util.Set;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.helper.StarSystemHelper;

public class MarketResultIntel extends BaseIntelPlugin {

    public final static String TAG = "stelnetMarket";

    private FactionAPI faction;
    private SectorEntityToken sectorEntityToken;
    private IntelSubject intelSubject;

    public MarketResultIntel(FactionAPI f, SectorEntityToken s, IntelSubject i) {
        faction = f;
        sectorEntityToken = s;
        intelSubject = i;
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        Color bulletColor = getBulletColorForMode(mode);
        info.addPara(intelSubject.getIntelTitle(), getTitleColor(mode), 0f);
        info.beginGridFlipped(300f, 1, Misc.getTextColor(), 80f, 10f);
        info.addToGrid(0, 0, sectorEntityToken.getName(), "Location", bulletColor);
        info.addToGrid(0, 1, faction.getDisplayName(), "Faction", bulletColor);
        info.addToGrid(0, 2, StarSystemHelper.getName(sectorEntityToken.getStarSystem()), "System", bulletColor);
        info.addGrid(3f);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        intelSubject.createSmallDescription(info, width, height);
    }

    @Override
    public String getIcon() {
        return intelSubject.getIcon();
    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return sectorEntityToken;
    }

    @Override
    public FactionAPI getFactionForUIColors() {
        return faction;
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(TAG);
        return tags;
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
    public String getSortString() {
        return getDistanceToPlayerLY("%07.2f");
    }

    @Override
    public boolean isNew() {
        return false;
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

    private String getDistanceToPlayerLY(String format) {
        float distanceToPlayerLY = Misc.getDistanceToPlayerLY(sectorEntityToken);
        return String.format(format, distanceToPlayerLY);
    }
}
