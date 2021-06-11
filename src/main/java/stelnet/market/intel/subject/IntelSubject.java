package stelnet.market.intel.subject;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.L10n;

public abstract class IntelSubject {

    protected String entity;
    protected MarketAPI market;

    public IntelSubject(String e, MarketAPI m) {
        entity = e;
        market = m;
    }

    public String getIcon() {
        return market.getFaction().getCrest();
    }

    public String getIntelTitle() {
        boolean canCheck = market != null;
        String titleId = canCheck && isStale() ? "marketIntelTitleStale" : "marketIntelTitle";
        return L10n.get(titleId, Misc.ucFirst(entity));
    }

    public abstract void createSmallDescription(TooltipMakerAPI info, float width, float height);

    public abstract boolean isAvailable();

    public abstract boolean isStale();

    protected void addBasicInfo(TooltipMakerAPI info, String basicInfo) {
        FactionAPI faction = market.getFaction();
        RelationshipAPI relationship = faction.getRelToPlayer();
        String reputation = relationship.getLevel().getDisplayName();
        String translatedRep = L10n.get("reputation" + reputation);
        String marketRepInfo = basicInfo + L10n.get("intelOwnerRelationship", translatedRep);
        info.addPara(marketRepInfo, 10f, Misc.getTextColor(), relationship.getRelColor(), translatedRep);
    }

    protected void addHeader(TooltipMakerAPI info, float width) {
        FactionAPI faction = market.getFaction();
        info.addSectionHeading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor(), Alignment.MID, 5f);
        info.addImage(faction.getLogo(), width, 128, 10f);
    }
}
