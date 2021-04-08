package stelnet.market;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

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
        return Misc.ucFirst(entity);
    }

    public String getIntelDesc() {
        return entity + "s";
    }

    public abstract void createSmallDescription(TooltipMakerAPI info, float width, float height);

    public abstract boolean canAcquire();

    public abstract boolean isAvailable();

    protected void addBasicInfo(TooltipMakerAPI info, String basicInfo) {
        FactionAPI faction = market.getFaction();
        RelationshipAPI relationship = faction.getRelToPlayer();
        String reputation = relationship.getLevel().getDisplayName();
        info.addPara(basicInfo + "The owner of this market is " + reputation.toLowerCase() + " towards you.", 10f,
                Misc.getTextColor(), relationship.getRelColor(), reputation.toLowerCase());
    }

    protected void addHeader(TooltipMakerAPI info, float width) {
        FactionAPI faction = market.getFaction();
        info.addSectionHeading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor(), Alignment.MID, 5f);
        info.addImage(faction.getLogo(), width, 128, 10f);
    }
}
