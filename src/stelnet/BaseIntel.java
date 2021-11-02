package stelnet;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import stelnet.util.StringUtils;
import uilib.RenderableIntel;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public abstract class BaseIntel extends RenderableIntel {

    private final FactionAPI faction;
    private final SectorEntityToken sectorEntityToken;

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        IntelInfo intelInfo = getIntelInfo();
        Color bulletColor = getBulletColorForMode(mode);
        info.addPara(intelInfo.getTitle(), getTitleColor(mode), 0);
        info.beginGridFlipped(300, 1, Misc.getTextColor(), 80, 10);
        info.addToGrid(0, 0, info.shortenString(intelInfo.getContent1(), 200), intelInfo.getHeader1(), bulletColor);
        info.addToGrid(0, 1, info.shortenString(intelInfo.getContent2(), 200), intelInfo.getHeader2(), bulletColor);
        info.addGrid(3);
    }

    @Override
    public FactionAPI getFactionForUIColors() {
        return getFaction();
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(getTag());
        return tags;
    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return getSectorEntityToken();
    }

    @Override
    public String getSortString() {
        return String.format("%07.2f", Misc.getDistanceToPlayerLY(sectorEntityToken));
    }

    @Override
    public boolean hasSmallDescription() {
        return true;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    protected String getFactionWithRel() {
        String reputation = faction.getRelToPlayer().getLevel().getDisplayName();
        String translatedRep = L10n.get("reputation" + reputation);
        return L10n.get("intelFactionWithRel", faction.getDisplayName(), translatedRep);
    }

    protected String getLocationName() {
        return sectorEntityToken.getName();
    }

    protected String getLocationNameWithSystem() {
        return StringUtils.getStarSystem(sectorEntityToken) + " - " + getLocationName();
    }

    protected abstract IntelInfo getIntelInfo();

    protected abstract String getTag();
}
