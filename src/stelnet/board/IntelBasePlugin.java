package stelnet.board;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.ui.SectorMapAPI;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.util.CommonL10n;
import stelnet.util.DistanceCalculator;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntel;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class IntelBasePlugin extends RenderableIntel {

    private final FactionAPI faction;
    private SectorEntityToken sectorEntityToken;
    private IntelSortTier sortTier = IntelSortTier.TIER_3;

    public IntelBasePlugin(FactionAPI faction, SectorEntityToken sectorEntityToken) {
        this(faction);
        this.sectorEntityToken = sectorEntityToken;
    }

    protected String getName() {
        return StelnetHelper.getStarSystemName(sectorEntityToken.getStarSystem(), true);
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
        return String.format("%07.2f", DistanceCalculator.getDistanceToPlayerLY(sectorEntityToken));
    }

    @Override
    public boolean hasSmallDescription() {
        return true;
    }

    protected String getFactionWithRel() {
        String reputation = faction.getRelToPlayer().getLevel().getDisplayName();
        return L10n.get(CommonL10n.INTEL_FACTION_WITH_REL, faction.getDisplayName(), reputation);
    }

    protected String getLocationName() {
        return sectorEntityToken.getName();
    }

    protected String getLocationNameWithSystem() {
        return StelnetHelper.getStarSystemName(sectorEntityToken.getStarSystem(), true) + " - " + getLocationName();
    }

    protected abstract String getTag();
}
