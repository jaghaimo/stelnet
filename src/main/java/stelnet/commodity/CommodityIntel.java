package stelnet.commodity;

import java.awt.Color;
import java.util.Set;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.price.Price;

@Getter
public class CommodityIntel extends BaseIntelPlugin {

    public final static String TAG = "stelnetCommodity";

    private final String action;
    private final CommoditySpecAPI commodity;
    private final MarketApiWrapper market;
    private final IntelTracker tracker;
    private final Price priceProvider;
    private final float price;

    public CommodityIntel(String action, CommoditySpecAPI commodity, MarketApiWrapper market, IntelTracker tracker,
            Price priceProvider) {
        this.action = action;
        this.commodity = commodity;
        this.market = market;
        this.tracker = tracker;
        this.priceProvider = priceProvider;
        this.price = market.getPriceAmount();
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        delete();
        ui.recreateIntelUI();
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI prompt) {
        if (buttonId == BUTTON_DELETE) {
            prompt.addPara("Are you sure you want to delete intel for this entry?", Misc.getTextColor(), 0f);
        }
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        Color bulletColor = getBulletColorForMode(mode);
        info.addPara(getTitle(), getTitleColor(mode), 0f);
        info.beginGridFlipped(300f, 1, Misc.getTextColor(), 80f, 10f);
        info.addToGrid(0, 0, market.getName(), "Location", bulletColor);
        info.addToGrid(0, 1, market.getDisplayName(), "Faction", bulletColor);
        info.addToGrid(0, 2, market.getStarSystem(), "System", bulletColor);
        info.addGrid(3f);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        FactionAPI faction = market.getFaction();
        RelationshipAPI relationship = faction.getRelToPlayer();
        String reputation = relationship.getLevel().getDisplayName();
        info.addSectionHeading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor(), Alignment.MID, 5f);
        info.addImage(faction.getLogo(), width, 128, 10f);
        if (isEnding()) {
            info.addPara("The original price of %s has changed to %s.", 5f, Misc.getTextColor(),
                    Misc.getHighlightColor(), Misc.getDGSCredits(price),
                    Misc.getDGSCredits(market.getPriceAmount()));
        }
        info.addPara("The owner of this market is " + reputation.toLowerCase() + " towards you.", 10f,
                Misc.getTextColor(), relationship.getRelColor(), reputation.toLowerCase());
        info.addPara("", 20f);
        addDeleteButton(info, width, "Delete entry");
    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return market.getPrimaryEntity();
    }

    @Override
    public String getIcon() {
        return commodity.getIconName();
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(CommodityIntel.TAG);
        return tags;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_1;
    }

    @Override
    public String getSortString() {
        return getTitle();
    }

    @Override
    public boolean hasLargeDescription() {
        return false;
    }

    @Override
    public boolean hasSmallDescription() {
        return true;
    }

    @Override
    public boolean isEnding() {
        return Math.abs(price - market.getPriceAmount()) > 1;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public void delete() {
        tracker.remove(this);
    }

    public String getCommodityId() {
        return commodity.getId();
    }

    private String getTitle() {
        return String.format("%s %s for %s", action, commodity.getName(), Misc.getDGSCredits(price));
    }
}
